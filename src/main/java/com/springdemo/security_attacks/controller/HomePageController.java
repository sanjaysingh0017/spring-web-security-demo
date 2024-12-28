package com.springdemo.security_attacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(path = {"/", "/getstudent"})
    public String greeting( Model model) {
        return "index";
    }

    @GetMapping("/deletestudent")
    public String deletestudent() {
        return "delete";
    }
}
