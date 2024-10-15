package com.example.spring.school.student.model.cache;

import com.example.spring.school.student.dao.entity.StudentEntity;
import com.example.spring.school.student.dao.repository.StudentRepository;
import com.example.spring.school.student.exception.NotFoundException;
import com.example.spring.school.student.constants.ExceptionConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentFallBack implements CacheFallBack<StudentEntity> {
    private final StudentRepository studentRepository;

    @Override
    public StudentEntity fetchFromDb(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ExceptionConstant.STUDENT_NOT_FOUND_CODE, ExceptionConstant.STUDENT_NOT_FOUND_MESSAGE));
    }
}
