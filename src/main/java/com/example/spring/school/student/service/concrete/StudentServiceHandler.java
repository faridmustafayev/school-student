package com.example.spring.school.student.service.concrete;

import com.example.spring.school.student.aspect.Log;
import com.example.spring.school.student.dao.entity.SchoolEntity;
import com.example.spring.school.student.dao.entity.StudentEntity;
import com.example.spring.school.student.dao.repository.StudentRepository;
import com.example.spring.school.student.exception.NotFoundException;
import com.example.spring.school.student.constants.ExceptionConstant;
import com.example.spring.school.student.model.enums.StudentStatus;
import com.example.spring.school.student.model.request.StudentRequest;
import com.example.spring.school.student.model.response.StudentResponse;
import com.example.spring.school.student.service.abstraction.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.spring.school.student.mapper.StudentMapper.STUDENT_MAPPER;

@Log
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceHandler implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public void saveStudent(SchoolEntity school, StudentRequest request) {
        StudentEntity studentEntity = STUDENT_MAPPER.buildStudentEntity(school, request);
        studentRepository.save(studentEntity);
    }

    @Override
    public StudentResponse getStudent(Long id) {
        StudentEntity student = fetchStudentIfExist(id);
        return STUDENT_MAPPER.buildStudentResponse(student);
    }

    @Override
    public void updateStudent(Long studentId, StudentStatus status) {
        StudentEntity student = fetchStudentIfExist(studentId);
        student.setStatus(status);
        studentRepository.save(student);
    }

//    @Retryable(retryFor = ObjectOptimisticLockingFailureException.class,
//            maxAttempts = 4, backoff = @Backoff(delay = 10000))
//    public void test() {
//        StudentEntity student1 = studentRepository.findById(4L).get();
//        StudentEntity student2 = studentRepository.findById(4L).get();
//        student1.setName("B");
//        student2.setName("G");
//        studentRepository.save(student2);
//        studentRepository.saveAndFlush(student1);
//    }

    private StudentEntity fetchStudentIfExist(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ExceptionConstant.STUDENT_NOT_FOUND_CODE, ExceptionConstant.STUDENT_NOT_FOUND_MESSAGE));
    }

//    @PostConstruct
//    public void init() {
//        test();
//    }

}
