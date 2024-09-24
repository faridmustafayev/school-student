package com.example.spring.school.student.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRequest {
    private String name;
    private String address;
    private SchoolDetailRequest request;
    private ActivityRequest activityRequest;
}
