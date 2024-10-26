package com.example.spring.school.student.model.cache;

public interface CacheFallBack<T> {
    T fetchFromDb(Long id);
}
