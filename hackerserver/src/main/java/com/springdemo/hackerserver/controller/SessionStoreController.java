package com.springdemo.hackerserver.controller;

import com.springdemo.hackerserver.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionStoreController {
    @Autowired
    private SessionService service;

    @GetMapping("/")
    public String homepage() {
        return "index";
    }
}
