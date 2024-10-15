package com.example.spring.school.student.dao.repository;

import com.example.spring.school.student.dao.entity.ActivityEntity;
import org.springframework.data.repository.CrudRepository;


public interface ActivityRepository extends CrudRepository<ActivityEntity, Long> {
}
