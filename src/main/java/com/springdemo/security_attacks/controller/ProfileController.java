package com.springdemo.security_attacks.controller;

import com.springdemo.security_attacks.dto.CustomUserDetails;
import com.springdemo.security_attacks.dto.ProfileDto;
import com.springdemo.security_attacks.entity.User;
import com.springdemo.security_attacks.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProfileController {

    @Autowired
    CustomUserDetailsService service;

    @GetMapping("/profile")
    @ResponseBody
    public User getProfile(Authentication authentication) {
        CustomUserDetails  userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

//    @PostMapping(value = "/profile", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public ResponseEntity updateProfile(Authentication authentication, ProfileDto profileDto) {
//        CustomUserDetails  userDetails = (CustomUserDetails) authentication.getPrincipal();
//        boolean updateUser = service.updateUser(userDetails, profileDto);
//        if (updateUser) {
//            return ResponseEntity.ok("success");
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("update operation failed");
//    }

    @PostMapping(value = "/profile", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateProfile(Authentication authentication, ProfileDto profileDto) {
        CustomUserDetails  userDetails = (CustomUserDetails) authentication.getPrincipal();
        boolean updateUser = service.updateUser(userDetails, profileDto);
        if (updateUser) {
            return "redirect:/";
        }
        return "redirect:/error";
    }

}
