package com.example.spring.school.student.service.abstraction;

import com.example.spring.school.student.model.request.SignInRequest;
import com.example.spring.school.student.model.response.TokenResponse;

public interface AuthService {
    TokenResponse signIn(SignInRequest request);

    void verifyToken(String accessToken);

    TokenResponse refreshTokens(String refreshToken);
}
