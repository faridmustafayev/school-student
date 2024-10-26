package com.example.spring.school.student.dao.repository;

import com.example.spring.school.student.dao.entity.SchoolEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {

//    @Override
//    @EntityGraph(attributePaths = {"students", "detail"})
//    @Query("SELECT DISTINCT s FROM SchoolEntity s JOIN FETCH s.students JOIN FETCH s.detail")
//    List<SchoolEntity> findAll();

}
