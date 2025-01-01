package com.springdemo.security_attacks.controller;

import com.springdemo.security_attacks.dto.CustomUserDetails;
import com.springdemo.security_attacks.entity.User;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.TEXT_PLAIN_VALUE})
public class SearchProductController {
    @GetMapping("/search")
    public String getProfile(@RequestParam String searchParam) {
        return searchParam;
    }
}
