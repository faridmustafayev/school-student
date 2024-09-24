package com.example.spring.school.student.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
