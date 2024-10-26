package com.example.spring.school.student.model.response;

import com.example.spring.school.student.model.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String grade;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
    private StudentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
