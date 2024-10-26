package com.example.spring.school.student.service.concrete;

import com.example.spring.school.student.cache.AuthCacheData;
import com.example.spring.school.student.constants.AuthConstants;
import com.example.spring.school.student.exception.AuthException;
import com.example.spring.school.student.jwt.AccessTokenClaimSet;
import com.example.spring.school.student.jwt.RefreshTokenClaimSet;
import com.example.spring.school.student.model.response.AuthPayloadResponse;
import com.example.spring.school.student.model.response.TokenResponse;
import com.example.spring.school.student.util.CacheUtil;
import com.example.spring.school.student.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import static com.example.spring.school.student.mapper.TokenMapper.buildAccessTokenClaimSet;
import static com.example.spring.school.student.mapper.TokenMapper.buildRefreshTokenClaimSet;
import static com.example.spring.school.student.constants.ExceptionConstant.TOKEN_EXPIRED_CODE;
import static com.example.spring.school.student.constants.ExceptionConstant.TOKEN_EXPIRED_Message;
import static com.example.spring.school.student.constants.ExceptionConstant.USER_UNAUTHORIZED_CODE;
import static com.example.spring.school.student.constants.ExceptionConstant.USER_UNAUTHORIZED_MESSAGE;
import static com.example.spring.school.student.util.CertificateKeyUtil.CERTIFICATE_KEY_UTIL;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {
    private final JwtUtil jwtUtil;
    private final CacheUtil cacheUtil;

    @Value("${jwt.accessToken.expiration.time}")
    private int accessTokenExpirationTime;

    @Value("${jwt.refreshToken.expiration.time}")
    private int refreshTokenExpirationTime;

    public TokenResponse generateToken(Long userId, int refreshTokenExpirationCount) {

        var accessTokenClaimSet = buildAccessTokenClaimSet(
                userId,
                jwtUtil.generateSessionExpirationTime(accessTokenExpirationTime)
        );

        var refreshTokenClaimSet = buildRefreshTokenClaimSet(
                userId,
                refreshTokenExpirationCount,
                jwtUtil.generateSessionExpirationTime(refreshTokenExpirationTime)
        );

        var keyPair = jwtUtil.generateKeyPair();

        var authCacheData = AuthCacheData.of(
                accessTokenClaimSet,
                Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded())
        );

        cacheUtil.saveToCache(AuthConstants.AUTH_CACHE_DATA_PREFIX + userId, authCacheData, AuthConstants.TOKEN_EXPIRE_DAY_COUNT, ChronoUnit.DAYS);

        var accessToken = jwtUtil.generateToken(accessTokenClaimSet, keyPair.getPrivate());
        var refreshToken = jwtUtil.generateToken(refreshTokenClaimSet, keyPair.getPrivate());

        return TokenResponse.of(accessToken, refreshToken);
    }

    public TokenResponse refreshTokens(String refreshToken) {
        var refreshTokenClaimsSet = jwtUtil.getClaimsFromToken(refreshToken, RefreshTokenClaimSet.class);
        var refreshTokenExpirationCount = refreshTokenClaimsSet.getCount() - 1;
        var userId = refreshTokenClaimsSet.getUserId();

        try {
            AuthCacheData authCacheData = cacheUtil.getBucket(AuthConstants.AUTH_CACHE_DATA_PREFIX + userId);

            if (authCacheData == null) throw new AuthException(USER_UNAUTHORIZED_CODE, USER_UNAUTHORIZED_MESSAGE, 401);

            var publicKey = CERTIFICATE_KEY_UTIL.getPublicKey(authCacheData.getPublicKey());

            jwtUtil.verifyToken(refreshToken, (RSAPublicKey) publicKey);

            if (jwtUtil.isRefreshTokenTimeExpired(refreshTokenClaimsSet)) {
                throw new AuthException(USER_UNAUTHORIZED_CODE, USER_UNAUTHORIZED_MESSAGE, 401);
            }

            if (jwtUtil.isRefreshTokenCountExpired(refreshTokenClaimsSet)) {
                throw new AuthException(USER_UNAUTHORIZED_CODE, USER_UNAUTHORIZED_MESSAGE, 401);
            }

            return generateToken(userId, refreshTokenExpirationCount);

        } catch (AuthException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new AuthException(USER_UNAUTHORIZED_CODE, USER_UNAUTHORIZED_MESSAGE, 401);
        }
    }


    public AuthPayloadResponse validateToken(String accessToken) {

        try {
            var accessTokenClaimsSet = jwtUtil.getClaimsFromToken(accessToken, AccessTokenClaimSet.class);
            var userId = accessTokenClaimsSet.getUserId();

            AuthCacheData authCacheData = cacheUtil.getBucket(AuthConstants.AUTH_CACHE_DATA_PREFIX + userId);

            if (authCacheData == null) throw new AuthException(TOKEN_EXPIRED_CODE, TOKEN_EXPIRED_Message, 406);

            var publicKey = CERTIFICATE_KEY_UTIL.getPublicKey(authCacheData.getPublicKey());

            jwtUtil.verifyToken(accessToken,(RSAPublicKey) publicKey);

            if (jwtUtil.isTokenExpired(authCacheData.getAccessTokenClaimSet().getExpirationTime())) {
                throw new AuthException(TOKEN_EXPIRED_CODE, TOKEN_EXPIRED_Message, 406);
            }

            return AuthPayloadResponse.of(userId);
        }catch (AuthException ex) {
            throw ex;
        }catch (Exception ex) {
            log.error(String.valueOf(ex));
            throw new AuthException(USER_UNAUTHORIZED_CODE, USER_UNAUTHORIZED_MESSAGE, 401);
        }

    }

}