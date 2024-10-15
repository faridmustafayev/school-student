package com.example.spring.school.student.mapper;

import com.example.spring.school.student.dao.entity.ActivityEntity;
import com.example.spring.school.student.dao.entity.SchoolDetailEntity;
import com.example.spring.school.student.dao.entity.SchoolEntity;
import com.example.spring.school.student.model.request.SchoolRequest;
import com.example.spring.school.student.model.response.SchoolResponse;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.spring.school.student.mapper.SchoolDetailMapper.SCHOOL_DETAIL_MAPPER;
import static com.example.spring.school.student.mapper.StudentMapper.STUDENT_MAPPER;
import static com.example.spring.school.student.model.enums.SchoolStatus.ACTIVE;

public enum SchoolMapper {
    SCHOOL_MAPPER;

    public SchoolEntity buildSchoolEntity(SchoolRequest request) {
        SchoolEntity schoolEntity = SchoolEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .activities(List.of(ActivityEntity.builder()
                        .name(request.getActivityRequest().getName())
                        .build()))
                .build();

        SchoolDetailEntity schoolDetailEntity = SchoolDetailEntity.builder()
                .school(schoolEntity)
                .principalName(request.getRequest().getPrincipalName())
                .status(ACTIVE)
                .build();

        schoolEntity.setDetail(schoolDetailEntity);

        return schoolEntity;
    }

    public SchoolResponse buildSchoolResponse(SchoolEntity school) {
        return SchoolResponse.builder()
                .id(school.getId())
                .name(school.getName())
                .address(school.getAddress())

                .students(school.getStudents().stream()
                        .map(STUDENT_MAPPER::buildStudentResponse)
                        .collect(Collectors.toList()))

                .detail(SCHOOL_DETAIL_MAPPER.buildSchoolDetailResponse(school.getDetail()))

                .build();
    }
}
