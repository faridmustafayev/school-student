package com.example.spring.school.student.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckPermissionDto {
    private String userId;
    private String productName;
    private String permissionName;
}
