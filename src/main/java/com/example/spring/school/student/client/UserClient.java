package com.example.spring.school.student.client;

import com.example.spring.school.student.client.decoder.CustomErrorDecoder;
import com.example.spring.school.student.model.client.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-user",
        url = "http://localhost:8080",
        path = "internal/",
        configuration = CustomErrorDecoder.class)
public interface UserClient {

    @GetMapping("v1/users/check-credential")
    UserResponseDto checkCredential(@RequestParam String username, @RequestParam String password);

}
