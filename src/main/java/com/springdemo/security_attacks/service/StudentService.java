package com.springdemo.security_attacks.service;

import com.springdemo.security_attacks.entity.Student;
import com.springdemo.security_attacks.repository.StudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepositoryImpl repository;

    public List<Student> getStudent(String id) {
        return repository.findStudent(id);
    }
}
