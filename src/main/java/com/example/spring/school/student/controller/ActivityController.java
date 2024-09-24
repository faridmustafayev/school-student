package com.example.spring.school.student.controller;

import com.example.spring.school.student.model.response.ActivityResponse;
import com.example.spring.school.student.service.abstraction.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @GetMapping("/{id}")
    public ActivityResponse getActivity(@PathVariable Long id){
        return activityService.getActivity(id);
    }

    @DeleteMapping
    public void deleteCache(){
        activityService.deleteCache();
    }

    @PutMapping("/{id}")
    public ActivityResponse updateCache(@PathVariable Long id){
        return activityService.updateCache(id);
    }

}
