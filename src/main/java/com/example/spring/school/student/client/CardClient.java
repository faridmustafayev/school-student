package com.example.spring.school.student.client;

import com.example.spring.school.student.client.decoder.CustomErrorDecoder;
import com.example.spring.school.student.model.client.CardResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-card",
        url = "${feign.client.url}",
        configuration = CustomErrorDecoder.class)
public interface CardClient {

    @GetMapping("internal/v1/cards/{cardId}")
    CardResponseDto getCard(@PathVariable Long cardId);
}
