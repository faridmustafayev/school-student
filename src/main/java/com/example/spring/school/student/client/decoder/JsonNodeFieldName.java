package com.example.spring.school.student.client.decoder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JsonNodeFieldName {
    CODE("code"),
    MESSAGE("message");
    private final String value;
}
