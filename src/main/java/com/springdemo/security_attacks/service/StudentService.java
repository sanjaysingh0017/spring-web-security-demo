package com.springdemo.security_attacks.service;

import com.springdemo.security_attacks.entity.MathsStudent;
import com.springdemo.security_attacks.repository.MathsStudentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private MathsStudentRepositoryImpl repository;

    public List<MathsStudent> getStudent(String id) {
        return repository.findStudent(id);
    }

    public Integer deleteStudent(String id) {
        return repository.deleteStudent(id);
    }
}
