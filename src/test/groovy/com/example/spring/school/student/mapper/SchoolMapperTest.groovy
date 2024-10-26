package com.example.spring.school.student.mapper

import com.example.spring.school.student.dao.entity.SchoolDetailEntity
import com.example.spring.school.student.dao.entity.SchoolEntity
import com.example.spring.school.student.dao.entity.StudentEntity
import com.example.spring.school.student.model.enums.SchoolStatus
import com.example.spring.school.student.model.request.ActivityRequest
import com.example.spring.school.student.model.request.SchoolDetailRequest
import com.example.spring.school.student.model.request.SchoolRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class SchoolMapperTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "BuildSchoolEntity"() {
        given:
        def schoolRequest = random.nextObject(SchoolRequest, "request", "activityRequest")
        def activityRequest = random.nextObject(ActivityRequest)
        def schoolDetailRequest = random.nextObject(SchoolDetailRequest)
        schoolRequest.request = schoolDetailRequest
        schoolRequest.activityRequest = activityRequest

        when:
        def schoolEntity = SchoolMapper.SCHOOL_MAPPER.buildSchoolEntity(schoolRequest)

        then:
        schoolEntity.name == schoolRequest.name
        schoolEntity.address == schoolRequest.address
        schoolEntity.activities[0].name == activityRequest.name
        schoolEntity.detail.principalName == schoolDetailRequest.principalName
        schoolEntity.detail.status == SchoolStatus.ACTIVE
        schoolEntity.students == null
    }


    def "BuildSchoolResponse"() {
        given:
        def schoolEntity = random.nextObject(SchoolEntity)
        def studentEntity = [random.nextObject(StudentEntity)]
        def detailEntity = random.nextObject(SchoolDetailEntity)
        schoolEntity.detail = detailEntity
        schoolEntity.students = studentEntity

        when:
        def schoolResponse = SchoolMapper.SCHOOL_MAPPER.buildSchoolResponse(schoolEntity)

        then:
        schoolResponse.id == schoolEntity.id
        schoolResponse.name == schoolEntity.name
        schoolResponse.address == schoolEntity.address
    }


}
