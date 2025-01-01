package com.springdemo.hackerserver.service;

import com.springdemo.hackerserver.entity.SessionEntity;
import com.springdemo.hackerserver.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public void saveSession(String sessionId) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setSessionId(sessionId);
        sessionRepository.saveAndFlush(sessionEntity);
    }
}
