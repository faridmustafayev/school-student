package com.example.spring.school.student.model.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CacheData<T> implements Serializable {
    private static final Long serialVersionUID = 1L;
    private T t;
}
