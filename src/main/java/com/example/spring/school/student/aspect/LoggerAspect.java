package com.example.spring.school.student.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggerAspect {
    @Pointcut("@within(Log)")
    public void loggerPointCut() {
    }

    @SneakyThrows
    @Around(value = "loggerPointCut()")
    public Object loggerAspect(ProceedingJoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        var method = signature.getMethod();

        if (method.isAnnotationPresent(LogIgnore.class)) {
            return jp.proceed();
        }

        log.info("ActionLog." + method.getName() + ".start");
        var response = jp.proceed();
        log.info("ActionLog." + method.getName() + ".success");
        return response;
    }

}
