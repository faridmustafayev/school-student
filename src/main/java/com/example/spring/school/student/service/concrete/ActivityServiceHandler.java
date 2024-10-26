package com.example.spring.school.student.service.concrete;

import com.example.spring.school.student.dao.entity.ActivityEntity;
import com.example.spring.school.student.dao.repository.ActivityRepository;
import com.example.spring.school.student.exception.NotFoundException;
import com.example.spring.school.student.constants.ExceptionConstant;
import com.example.spring.school.student.model.request.ActivityRequest;
import com.example.spring.school.student.model.response.ActivityResponse;
import com.example.spring.school.student.service.abstraction.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.example.spring.school.student.mapper.ActivityMapper.ACTIVITY_MAPPER;

@Service
@RequiredArgsConstructor
public class ActivityServiceHandler implements ActivityService {
    private final ActivityRepository activityRepository;


    @Override
    public void saveActivity(ActivityRequest request) {
        System.out.println("I worked");
        ActivityEntity activityEntity = ACTIVITY_MAPPER.buildActivityEntity(request);
        activityRepository.save(activityEntity);
    }

    @Cacheable("activities")
    @Override
    public ActivityResponse getActivity(Long id) {
        ActivityEntity activity = fetchActivityIfExist(id);
        return ACTIVITY_MAPPER.buildActivityResponse(activity);
    }

    @CacheEvict(allEntries = true, value = "activities")
    @Override
    public void deleteCache() {

    }


    @CachePut(value = "activities")
    @Override
    public ActivityResponse updateCache(Long id) {
        return getActivity(id);
    }

    private ActivityEntity fetchActivityIfExist(Long id) {
        return activityRepository.findById(id).orElseThrow(()->
                new NotFoundException(ExceptionConstant.ACTIVITY_NOT_FOUND_CODE, ExceptionConstant.ACTIVITY_NOT_FOUND_MESSAGE));
    }

}
