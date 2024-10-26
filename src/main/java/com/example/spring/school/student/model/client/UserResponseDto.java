package com.example.spring.school.student.model.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private Integer age;
    private String birthPlace;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserStatus status;
    private String password;
}
