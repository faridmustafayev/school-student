package com.example.spring.school.student.service.abstraction;

import com.example.spring.school.student.model.request.SchoolRequest;
import com.example.spring.school.student.model.request.StudentRequest;
import com.example.spring.school.student.model.response.SchoolResponse;

import java.util.List;

public interface SchoolService {
    void saveSchool(SchoolRequest schoolRequest);

    void addStudentToSchool(Long id, StudentRequest request);

    List<SchoolResponse> getSchools();

    SchoolResponse getSchool(Long id);

}
