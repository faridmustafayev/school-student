package com.example.spring.school.student.controller;

import com.example.spring.school.student.model.response.StudentResponse;
import com.example.spring.school.student.service.abstraction.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

}
