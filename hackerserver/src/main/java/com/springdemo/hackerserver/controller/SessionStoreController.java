package com.springdemo.hackerserver.controller;

import com.springdemo.hackerserver.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hacker")
public class SessionStoreController {
    @Autowired
    private SessionService service;

    @GetMapping("/session")
    public void storeSession(@RequestParam String sessionId) {
        service.saveSession(sessionId);
    }
}
