package com.example.spring.school.student.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String name;
    private String grade;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
}
