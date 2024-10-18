package com.example.spring.school.student.queue;

import com.example.spring.school.student.model.queue.GetStudentDto;
import com.example.spring.school.student.service.abstraction.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestListener {

    private final StudentService studentService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${rabbitmq.queue.test}")
    @SneakyThrows
    public void consume(String message) {
        var data = objectMapper.readValue(message, GetStudentDto.class);
        studentService.getStudent(data.getId());
    }

}
