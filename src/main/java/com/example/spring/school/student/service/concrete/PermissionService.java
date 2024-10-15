package com.example.spring.school.student.service.concrete;

import com.example.spring.school.student.client.PermissionClient;
import com.example.spring.school.student.model.client.CheckPermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "permissionService")
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionClient permissionClient;

    public boolean checkPermission(String userId, String permissionName, String productName) {
        boolean result;

        try {
            result = permissionClient.checkPermission(
                    new CheckPermissionDto(userId, permissionName, productName)
            );
        }catch (Exception ex) {
            return false;
        }

        return result;
    }

}
