package com.example.spring.school.student.queue;

import com.example.spring.school.student.exception.QueueException;
import com.example.spring.school.student.model.queue.GetStudentDto;
import com.example.spring.school.student.service.abstraction.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestListener {

    private final StudentService studentService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${rabbitmq.queue.test}")
    public void consume(String message) {
        try {
            var data = objectMapper.readValue(message, GetStudentDto.class);
            studentService.getStudent(data.getId());
        }catch (JsonProcessingException ex) {
            log.error("ActionLog.consume.error message invalid format: {}", message);
        }catch (Exception ex) {
            throw new QueueException();
        }
    }

}
