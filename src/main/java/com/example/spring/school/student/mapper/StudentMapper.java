package com.example.spring.school.student.mapper;

import com.example.spring.school.student.dao.entity.SchoolEntity;
import com.example.spring.school.student.dao.entity.StudentEntity;
import com.example.spring.school.student.model.request.StudentRequest;
import com.example.spring.school.student.model.response.StudentResponse;

import static com.example.spring.school.student.model.enums.StudentStatus.ACTIVE;

public enum StudentMapper {
    STUDENT_MAPPER;

    public StudentEntity buildStudentEntity(SchoolEntity schoolEntity,
                                            StudentRequest studentRequest) {
        return StudentEntity.builder()
                .school(schoolEntity)
                .name(studentRequest.getName())
                .status(ACTIVE)
                .grade(studentRequest.getGrade())
                .dateOfBirth(studentRequest.getDateOfBirth())
                .enrollmentDate(studentRequest.getEnrollmentDate())
                .build();
    }

    public StudentResponse buildStudentResponse(StudentEntity student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .grade(student.getGrade())
                .dateOfBirth(student.getDateOfBirth())
                .enrollmentDate(student.getEnrollmentDate())
                .status(student.getStatus())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }
}
