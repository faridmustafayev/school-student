package com.example.spring.school.student.service.abstraction;

import com.example.spring.school.student.dao.entity.SchoolEntity;
import com.example.spring.school.student.model.enums.StudentStatus;
import com.example.spring.school.student.model.request.StudentRequest;
import com.example.spring.school.student.model.response.StudentResponse;

public interface StudentService {
    void saveStudent(SchoolEntity school, StudentRequest request);

    StudentResponse getStudent(Long id);

    void updateStudent(Long studentId, StudentStatus status);

//    void test();
}
