package com.example.spring.school.student.service.concrete;

import com.example.spring.school.student.service.abstraction.MathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MathServiceHandler implements MathService {
    @Override
    public Long sum(Long firstNumber, Long secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            return 0L;
        }
        return firstNumber + secondNumber;
    }
}
