package com.arturk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "Welcome to Spring Security Udemy Course Application with security";
    }

}
