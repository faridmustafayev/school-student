package com.example.spring.school.student.mapper;

import com.example.spring.school.student.dao.entity.SchoolDetailEntity;
import com.example.spring.school.student.model.response.SchoolDetailResponse;

public enum SchoolDetailMapper {
    SCHOOL_DETAIL_MAPPER;

    public SchoolDetailResponse buildSchoolDetailResponse(SchoolDetailEntity detail) {
        return SchoolDetailResponse.builder()
                .principalName(detail.getPrincipalName())
                .status(detail.getStatus())
                .createdAt(detail.getCreatedAt())
                .updatedAt(detail.getUpdatedAt())
                .build();
    }
}
