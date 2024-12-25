package com.springdemo.security_attacks.repository;

import com.springdemo.security_attacks.entity.MathsStudent;

import java.util.List;


public interface MathsStudentRepository {
    public List<MathsStudent> findStudent(String id);

    public Integer deleteStudent(String id);
}
