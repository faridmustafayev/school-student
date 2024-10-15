package com.example.spring.school.student.service.abstraction;

import com.example.spring.school.student.model.request.ActivityRequest;
import com.example.spring.school.student.model.response.ActivityResponse;

public interface ActivityService {
    void saveActivity(ActivityRequest request);

    ActivityResponse getActivity(Long id);

    void deleteCache();

    ActivityResponse updateCache(Long id);
}
