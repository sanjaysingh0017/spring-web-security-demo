package com.springdemo.security_attacks.controller;

import com.springdemo.security_attacks.dto.CustomUserDetails;
import com.springdemo.security_attacks.entity.User;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProfileController {

    @GetMapping("/profile")
    public User getProfile(Authentication authentication) {
        CustomUserDetails  userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

}
