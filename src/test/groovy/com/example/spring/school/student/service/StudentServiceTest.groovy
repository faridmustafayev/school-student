package com.example.spring.school.student.service

import com.example.spring.school.student.dao.entity.SchoolEntity
import com.example.spring.school.student.dao.entity.StudentEntity
import com.example.spring.school.student.dao.repository.StudentRepository
import com.example.spring.school.student.exception.NotFoundException
import com.example.spring.school.student.model.enums.StudentStatus
import com.example.spring.school.student.model.request.StudentRequest
import com.example.spring.school.student.service.abstraction.StudentService
import com.example.spring.school.student.service.concrete.StudentServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.spring.school.student.mapper.StudentMapper.STUDENT_MAPPER

class StudentServiceTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    StudentRepository studentRepository
    StudentService studentService

    def setup() {
        studentRepository = Mock()
        studentService = new StudentServiceHandler(studentRepository)
    }

    // getStudent

    def "TestGetStudent success case"() {
        given:
        def id = random.nextObject(Long)
        def studentEntity = random.nextObject(StudentEntity)

        when:
        def actual = studentService.getStudent(id)

        then:
        1* studentRepository.findById(id) >> Optional.of(studentEntity)
        actual.id == studentEntity.id
        actual.name == studentEntity.name
        actual.grade == studentEntity.grade
        actual.dateOfBirth == studentEntity.dateOfBirth
        actual.enrollmentDate == studentEntity.enrollmentDate
        actual.status == studentEntity.status
        actual.createdAt == studentEntity.createdAt
        actual.updatedAt == studentEntity.updatedAt
    }

    def "TestGetStudent not found case"() {
        given:
        def id = random.nextObject(Long)

        when:
        studentService.getStudent(id)

        then:
        1* studentRepository.findById(id) >> Optional.empty()
        NotFoundException ex = thrown()
        ex.code == "STUDENT_NOT_FOUND"
        ex.message == "Student not found"
    }

    // updateStudent

    def "TestUpdateStudent success case"() {
        given:
        def id = random.nextObject(Long)
        def status = StudentStatus.IN_PROGRESS
        def student = random.nextObject(StudentEntity)
        student.status = StudentStatus.ACTIVE

        when:
        studentService.updateStudent(id, status)

        then:
        1 * studentRepository.findById(id) >> Optional.of(student)
        1 * studentRepository.save(student)
        student.status == status
    }


    def "TestUpdateStudent error case"() {
        given:
        def id = random.nextObject(Long)
        def status = random.nextObject(StudentStatus)

        when:
        studentService.updateStudent(id, status)

        then:
        1 * studentRepository.findById(id) >> Optional.empty()
        0 * studentRepository.save(_)
        thrown(NotFoundException)
    }


    // saveStudent

    def "TestSaveStudent"() {
        given:
        def schoolEntity = random.nextObject(SchoolEntity)
        def studentRequest = random.nextObject(StudentRequest)
        def studentEntity = STUDENT_MAPPER.buildStudentEntity(schoolEntity, studentRequest)

        when:
        studentService.saveStudent(schoolEntity, studentRequest)

        then:
        1* studentRepository.save(studentEntity)
    }

}
