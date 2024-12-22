package com.springdemo.security_attacks.repository;

import com.springdemo.security_attacks.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StudentRepository {
    public List<Student> findStudent(String id);
}
