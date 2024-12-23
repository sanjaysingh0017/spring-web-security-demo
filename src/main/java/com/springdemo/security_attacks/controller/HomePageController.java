package com.springdemo.security_attacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {

    @GetMapping(path = {"/", "/getstudent"})
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/deletestudent")
    public String deletestudent() {
        return "delete";
    }
}
