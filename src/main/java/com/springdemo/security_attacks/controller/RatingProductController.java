package com.springdemo.security_attacks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.TEXT_PLAIN_VALUE})
public class RatingProductController {

    Logger log = LoggerFactory.getLogger(RatingProductController.class);
    @PostMapping(value = "/rating", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String rating( String rating) {
        log.info("Rating received from user : " + rating);
        return rating;
    }
}
