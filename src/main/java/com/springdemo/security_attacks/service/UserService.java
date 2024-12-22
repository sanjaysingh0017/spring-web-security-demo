package com.springdemo.security_attacks.service;

import com.springdemo.security_attacks.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepositoryImpl repository;

    public Integer getUser(String id) {
        return repository.findUser(id);
    }
}
