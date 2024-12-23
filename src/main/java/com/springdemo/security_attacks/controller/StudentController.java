package com.springdemo.security_attacks.controller;

import com.springdemo.security_attacks.entity.Student;
import com.springdemo.security_attacks.service.StudentService;
import com.springdemo.security_attacks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private UserService userService;

//    @GetMapping("/student")
//    public List<Student> findStudent(@RequestParam String id) {
//        return service.getStudent(id);
//    }

    @GetMapping("/user")
    public Integer getUser(@RequestParam String id) {
        return userService.getUser(id);
    }

    @GetMapping("/student")
    public String findStudent(@RequestParam String id, Model model) {
        model.addAttribute("students", service.getStudent(id));
        return "result";
    }

    @Transactional
    @GetMapping("/student/delete")
    public String deleteStudent(@RequestParam String id, Model model) {
        model.addAttribute("number", service.deleteStudent(id));
        return "deleteresult";
    }
}
