package com.springdemo.security_attacks.controller;

import jakarta.validation.constraints.Pattern;
import org.owasp.encoder.Encode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*")
public class HomePageController {

    @GetMapping(path = {"/"})
    public String greeting(@RequestParam(defaultValue = "product") String searchParam, Model model) {
        model.addAttribute("searchParam", searchParam);
        if (searchParam.equals("product")) {
            return "index";
        }
        return "redirect:/pagenotfound?searchParam="+searchParam;
    }

    @GetMapping(value = "/pagenotfound" )
    public String notfound(@RequestParam(defaultValue = "product") /*@Pattern(regexp = "^[a-zA-Z0-9]*$")*/ String searchParam, Model model) {

        //String htmlContent = Encode.forHtmlContent(searchParam);
        //String cssString = Encode.forCssString(htmlContent);
        model.addAttribute("searchParam", searchParam);
        return "notfound";
    }

}
