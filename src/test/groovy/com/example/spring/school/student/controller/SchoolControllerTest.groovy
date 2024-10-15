package com.example.spring.school.student.controller

import com.example.spring.school.student.exception.ErrorHandler
import com.example.spring.school.student.model.request.SchoolRequest
import com.example.spring.school.student.model.request.StudentRequest
import com.example.spring.school.student.model.response.SchoolResponse
import com.example.spring.school.student.service.abstraction.SchoolService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class SchoolControllerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    SchoolService schoolService
    MockMvc mockMvc

    void setup() {
        schoolService = Mock()
        def schoolController = new SchoolController(schoolService)
        mockMvc = MockMvcBuilders.standaloneSetup(schoolController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }


    def "TestSaveSchool"() {
        given:
        def schoolRequest = random.nextObject(SchoolRequest)
        def url = "/v1/schools"
        def requestBody = """
                {
                    "name": "$schoolRequest.name",
                    "address": "$schoolRequest.address",
                    "request": {
                          "principalName": "$schoolRequest.request.principalName"
                    },
                    "activityRequest": {
                          "name": "$schoolRequest.activityRequest.name"
                    }
                }
        """

        when:
        def result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andReturn()

        then:
        1 * schoolService.saveSchool(schoolRequest)

        result.response.status == HttpStatus.CREATED.value()
    }


    def "TestAddStudentToSchool"() {
        given:
        def id = random.nextObject(Long)
        def studentRequest = random.nextObject(StudentRequest)
        def url = "/v1/schools/$id/student"
        def requestBody = """
                {
                    "name": "$studentRequest.name",
                    "grade": "$studentRequest.grade",
                    "dateOfBirth": "$studentRequest.dateOfBirth",
                    "enrollmentDate": "$studentRequest.enrollmentDate"
                }
        """

        when:
        def result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andReturn()

        then:
        1 * schoolService.addStudentToSchool(id, studentRequest)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }


    def "TestGetSchool"() {
        given:
        def id = random.nextObject(Long)
        def url = "/v1/schools/$id"
        def schoolResponse = random.nextObject(SchoolResponse)

        def schoolResponse1 = """
                {
                    "id": "$schoolResponse.id"
                    "name": "$schoolResponse.name"
                    "address": "$schoolResponse.address"
                    "activities": {
                            "id": "$schoolResponse.activities.id"
                            "name": "$schoolResponse.activities.name"
                    },
                    "students": {
                            "id": "$schoolResponse.students.id"
                            "name": "$schoolResponse.students.name"
                            "grade": "$schoolResponse.students.grade"
                            "dateOfBirth": "$schoolResponse.students.dateOfBirth"
                            "enrollmentDate": "$schoolResponse.students.enrollmentDate"
                            "status": "$schoolResponse.students.status"
                            "createdAt": "$schoolResponse.students.createdAt"
                            "updatedAt": "$schoolResponse.students.updatedAt"
                    },
                    "detail": {
                         "principalName": "$schoolResponse.detail.principalName"
                         "status": "$schoolResponse.detail.status"
                         "createdAt": "$schoolResponse.detail.createdAt"
                         "updatedAt": "$schoolResponse.detail.updatedAt"
                    }
                }
        """

        when:
        def result = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(schoolResponse1)
        ).andReturn()

        then:
        1 * schoolService.getSchool(id) >> schoolResponse1

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(schoolResponse1.toString(), result.response.contentAsString.toString(), true)
    }

    def "TestGetSchools"() {
        def url = "/v1/schools"
        def schoolResponse = [random.nextObject(SchoolResponse)]

        def schoolResponse1 = """
                {
                    "id": "$schoolResponse.id"
                    "name": "$schoolResponse.name"
                    "address": "$schoolResponse.address"
                    "activities": "$schoolResponse.activities",
                    "students": "$schoolResponse.students",
                    "detail": {
                         "principalName": "$schoolResponse.detail.principalName"
                         "status": "$schoolResponse.detail.status"
                         "createdAt": "$schoolResponse.detail.createdAt"
                         "updatedAt": "$schoolResponse.detail.updatedAt"
                    }
                }
        """

        when:
        def result = mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(schoolResponse1)
        ).andReturn()

        then:
        1 * schoolService.getSchools()

        def response = result.response
        // the same here as 138 line
    }

}
