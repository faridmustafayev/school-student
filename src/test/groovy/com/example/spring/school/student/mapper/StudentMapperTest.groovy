package com.example.spring.school.student.mapper

import com.example.spring.school.student.dao.entity.SchoolEntity
import com.example.spring.school.student.dao.entity.StudentEntity
import com.example.spring.school.student.model.request.StudentRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class StudentMapperTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "BuildStudentEntity"() {
        given:
        def schoolEntity = random.nextObject(SchoolEntity)
        def studentRequest = random.nextObject(StudentRequest)

        when:
        def studentEntity = StudentMapper.STUDENT_MAPPER.buildStudentEntity(schoolEntity, studentRequest)

        then:
        studentEntity.name == studentRequest.name
        studentEntity.grade == studentRequest.grade
        studentEntity.dateOfBirth == studentRequest.dateOfBirth
        studentEntity.enrollmentDate == studentRequest.enrollmentDate
        studentEntity.createdAt == null
        studentEntity.id == null
        studentEntity.updatedAt == null
    }

    def "BuildStudentResponse"() {
        given:
        def studentEntity = random.nextObject(StudentEntity)

        when:
        def response = StudentMapper.STUDENT_MAPPER.buildStudentResponse(studentEntity)

        then:
        response.id == studentEntity.id
        response.name == studentEntity.name
        response.grade == studentEntity.grade
        response.dateOfBirth == studentEntity.dateOfBirth
        response.status == studentEntity.status
        response.createdAt == studentEntity.createdAt
        response.updatedAt == studentEntity.updatedAt
    }

}
