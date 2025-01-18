package com.springdemo.security_attacks.controller;

import com.giffing.bucket4j.spring.boot.starter.context.RateLimiting;
import com.springdemo.security_attacks.dto.CustomUserDetails;
import com.springdemo.security_attacks.dto.ProfileDto;
import com.springdemo.security_attacks.entity.User;
import com.springdemo.security_attacks.service.CustomUserDetailsService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

//@RestController
@Controller
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProfileController {

    Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    CustomUserDetailsService service;

    @GetMapping("/profile")
    @ResponseBody
    //@TimeLimiter(name = "timelimit")
    public CompletableFuture<ResponseEntity<?>> getProfile(Authentication authentication) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                CustomUserDetails  userDetails = (CustomUserDetails) authentication.getPrincipal();
                return ResponseEntity.ok(userDetails.getUser());
            } catch (Exception e) {
                throw new RuntimeException("Error while fetching all items from the database");
            }
        });
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

    @GetMapping()
    public void test(@RequestParam Integer input) { //input = 123456789;
        int arr[] = new int[input];
        for(int i=0; i<= arr.length;i++) {
            System.out.println(arr[i]);
        }
    }

}
