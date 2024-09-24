package com.example.spring.school.student.mapper;

import com.example.spring.school.student.dao.entity.ActivityEntity;
import com.example.spring.school.student.model.request.ActivityRequest;
import com.example.spring.school.student.model.response.ActivityResponse;

public enum ActivityMapper {
    ACTIVITY_MAPPER;

    public ActivityEntity buildActivityEntity(ActivityRequest request) {
        return ActivityEntity.builder()
                .name(request.getName())
                .build();
    }

    public ActivityResponse buildActivityResponse(ActivityEntity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
                .name(activity.getName())
                .build();
    }
}
