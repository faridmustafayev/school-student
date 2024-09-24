package com.example.spring.school.student.service.concrete;

import com.example.spring.school.student.aspect.Log;
import com.example.spring.school.student.aspect.LogIgnore;
import com.example.spring.school.student.client.CardClient;
import com.example.spring.school.student.dao.entity.SchoolEntity;
import com.example.spring.school.student.dao.repository.SchoolRepository;
import com.example.spring.school.student.exception.NotFoundException;
import com.example.spring.school.student.constants.ExceptionConstant;
import com.example.spring.school.student.model.request.SchoolRequest;
import com.example.spring.school.student.model.request.StudentRequest;
import com.example.spring.school.student.model.response.SchoolResponse;
import com.example.spring.school.student.service.abstraction.SchoolService;
import com.example.spring.school.student.service.abstraction.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.spring.school.student.mapper.SchoolMapper.SCHOOL_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
@Log
public class SchoolServiceHandler implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentService studentService;
    private final CardClient cardClient;

    @Override
    @LogIgnore
    public void saveSchool(SchoolRequest schoolRequest) {
//        CardResponseDto card = cardClient.getCard(2L);
//        log.info("Our card: {}", card);
        SchoolEntity schoolEntity = SCHOOL_MAPPER.buildSchoolEntity(schoolRequest);
        schoolRepository.save(schoolEntity);
    }

    @Override
    public void addStudentToSchool(Long id, StudentRequest request) {
        SchoolEntity school = fetchSchoolIfExist(id);
        studentService.saveStudent(school, request);
    }

    @Transactional
    @Override
    public SchoolResponse getSchool(Long id) {
        SchoolEntity school = fetchSchoolIfExist(id);
        return SCHOOL_MAPPER.buildSchoolResponse(school);
    }

    @Transactional
    @Override
    public List<SchoolResponse> getSchools() {
        List<SchoolEntity> schools = schoolRepository.findAll();
        List<SchoolResponse> schoolResponses = schools.stream()
                .map(SCHOOL_MAPPER::buildSchoolResponse)
                .toList();

        schoolResponses.forEach(it-> {
            it.getStudents().forEach(student-> System.out.println(student.getId()));
        });

        return schoolResponses;
    }

    private SchoolEntity fetchSchoolIfExist(Long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(ExceptionConstant.SCHOOL_NOT_FOUND_CODE, ExceptionConstant.SCHOOL_NOT_FOUND_MESSAGE));
    }
}
