package com.example.spring.school.student.service.concrete;

import com.example.spring.school.student.client.UserClient;
import com.example.spring.school.student.model.client.UserResponseDto;
import com.example.spring.school.student.model.request.SignInRequest;
import com.example.spring.school.student.model.response.TokenResponse;
import com.example.spring.school.student.service.abstraction.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceHandler implements AuthService {
    private final TokenService tokenService;
    private final UserClient userClient;

    @Override
    public TokenResponse signIn(SignInRequest request) {
        UserResponseDto userResponse = userClient.checkCredential(request.getUsername(), request.getPassword());
        return tokenService.generateToken(userResponse.getId(), 2);
    }

    @Override
    public void verifyToken(String accessToken) {
        tokenService.validateToken(accessToken);
    }

    @Override
    public TokenResponse refreshTokens(String refreshToken) {
        return tokenService.refreshTokens(refreshToken);
    }
}
