package com.example.spring.school.student.service

import com.example.spring.school.student.client.UserClient
import com.example.spring.school.student.model.request.SignInRequest
import com.example.spring.school.student.service.abstraction.AuthService
import com.example.spring.school.student.service.concrete.AuthServiceHandler
import com.example.spring.school.student.service.concrete.TokenService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class AuthServiceTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    TokenService tokenService
    UserClient userClient
    AuthService authService

    def setup() {
        tokenService = Mock()
        userClient = Mock()
        authService = new AuthServiceHandler(tokenService, userClient)
    }

    def "TestSignIn"() {
        def signInRequest = random.nextObject(SignInRequest)

    }

}
