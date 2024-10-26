package com.example.spring.school.student.service.concrete;

import com.example.spring.school.student.model.cache.CacheData;
import com.example.spring.school.student.model.cache.CacheFallBack;
import com.example.spring.school.student.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheServiceHandler<T> {
    private final CacheUtil cacheUtil;
    private final Map<String, CacheFallBack<T>> cacheFallBackMap;

    public void save(Long id, T value) {
        var cacheKey = "spring-school-student:school-id:" + id;
        var data = new CacheData<>(value);
        cacheUtil.saveToCache(cacheKey, data, 1L, ChronoUnit.MINUTES);
    }

    public T get(String cacheKey, Long id, String serviceName) {
        CacheData<T> cacheData = null;

        try {
            cacheData = cacheUtil.getBucket(cacheKey);
            if (cacheData != null) {
                return cacheData.getT();
            }
        }catch (Exception ex) {
            log.error("Redis error: " + ex.getMessage());

            CacheFallBack<T> cacheFallBack = cacheFallBackMap.get(serviceName);
            if (cacheFallBack != null) {
                T data = cacheFallBack.fetchFromDb(id);
                if (data != null) {
                    save(id, data);
                    return data;
                }
            }
        }
        return null;
    }
}
