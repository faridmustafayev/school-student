package com.example.spring.school.student.client;

import com.example.spring.school.student.model.client.CheckPermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ms-permission",
        url = "http://localhost:8080"
)
public interface PermissionClient {

    @PostMapping("/v1/permission/check")
    boolean checkPermission(@RequestBody CheckPermissionDto dto);
}
