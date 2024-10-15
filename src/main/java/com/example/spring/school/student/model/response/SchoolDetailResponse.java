package com.example.spring.school.student.model.response;

import com.example.spring.school.student.dao.entity.SchoolEntity;
import com.example.spring.school.student.model.enums.SchoolStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolDetailResponse {
    private String principalName;
    private SchoolStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
