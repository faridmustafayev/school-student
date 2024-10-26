package com.example.spring.school.student.mapper

import com.example.spring.school.student.dao.entity.ActivityEntity
import com.example.spring.school.student.model.request.ActivityRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ActivityMapperTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "BuildActivityEntity"() {
        given:
        def activityRequest = random.nextObject(ActivityRequest)

        when:
        def activityEntity = ActivityMapper.ACTIVITY_MAPPER.buildActivityEntity(activityRequest)

        then:
        activityEntity.name == activityRequest.name
        activityEntity.id == null
    }


    def "BuildActivityResponse"() {
        given:
        def activityEntity = random.nextObject(ActivityEntity)

        when:
        def activityResponse = ActivityMapper.ACTIVITY_MAPPER.buildActivityResponse(activityEntity)

        then:
        activityResponse.id == activityEntity.id
        activityResponse.name == activityResponse.name
    }

}
