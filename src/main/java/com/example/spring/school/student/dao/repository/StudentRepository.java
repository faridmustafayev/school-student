package com.example.spring.school.student.dao.repository;

import com.example.spring.school.student.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
