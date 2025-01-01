package com.springdemo.security_attacks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {

    @GetMapping(path = {"/"})
    public String greeting(@RequestParam(defaultValue = "product") String searchParam, Model model) {
        model.addAttribute("searchParam", searchParam);
        if (searchParam.equals("product")) {
            return "index";
        }
        return "redirect:/pagenotfound?searchParam="+searchParam;
    }

    @GetMapping("/pagenotfound")
    public String notfound(@RequestParam(defaultValue = "product") String searchParam, Model model) {
        model.addAttribute("searchParam", searchParam);
        return "notfound";
    }

}
