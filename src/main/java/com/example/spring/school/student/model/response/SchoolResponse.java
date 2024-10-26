package com.example.spring.school.student.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SchoolResponse {
    private Long id;
    private String name;
    private String address;
    private List<ActivityResponse> activities = new ArrayList<>();
    private List<StudentResponse> students = new ArrayList<>();
    private SchoolDetailResponse detail;
}
