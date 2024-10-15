package com.example.spring.school.student.controller;

import com.example.spring.school.student.model.request.SignInRequest;
import com.example.spring.school.student.model.response.TokenResponse;
import com.example.spring.school.student.service.abstraction.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public TokenResponse signIn(@RequestBody SignInRequest request) {
        return authService.signIn(request);
    }

    @PostMapping("/verify")
    public void verifyToken(String accessToken) {
        authService.verifyToken(accessToken);
    }

    @PostMapping("/refresh")
    public TokenResponse refreshToken(String refreshToken) {
        return authService.refreshTokens(refreshToken);
    }

}
