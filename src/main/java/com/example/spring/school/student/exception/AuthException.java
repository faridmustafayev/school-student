package com.example.spring.school.student.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
    private final int httpStatusCode;
    private String code;

    public AuthException(String code, String message, int httpStatusCode) {
        super(message);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }
}
