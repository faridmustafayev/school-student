package com.example.spring.school.student.controller;

import com.example.spring.school.student.model.request.SchoolRequest;
import com.example.spring.school.student.model.request.StudentRequest;
import com.example.spring.school.student.model.response.SchoolResponse;
import com.example.spring.school.student.service.abstraction.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveSchool(@RequestBody SchoolRequest schoolRequest) {
        schoolService.saveSchool(schoolRequest);
    }

    @PostMapping("/{id}/student")
    @ResponseStatus(NO_CONTENT)
    @PreAuthorize("@permissionService.checkPermission(#id, 'CARDS', 'VIEW')")
    public void addStudentToSchool(@PathVariable Long id, @RequestBody StudentRequest request){
        schoolService.addStudentToSchool(id, request);
    }

    @GetMapping("/{id}")
    public SchoolResponse getSchool(@PathVariable Long id){
        return schoolService.getSchool(id);
    }

    @GetMapping
    public List<SchoolResponse> getSchools(){
        return schoolService.getSchools();
    }

}
