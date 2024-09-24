package com.example.spring.school.student.util;

import lombok.SneakyThrows;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import static com.example.spring.school.student.constants.AuthConstants.RSA;
import static org.springframework.util.Base64Utils.decodeFromString;

public enum CertificateKeyUtil {
    CERTIFICATE_KEY_UTIL;

    @SneakyThrows
    public PublicKey getPublicKey(String publicKey) {
        var keyFactory = KeyFactory.getInstance(RSA);
        var keySpec = new X509EncodedKeySpec(decodeFromString(publicKey));
        return keyFactory.generatePublic(keySpec);
    }

}
