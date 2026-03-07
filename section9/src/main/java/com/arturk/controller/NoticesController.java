package com.arturk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping(value = "/notices")
    public String getNoticesDetails() {
        return "Here are the notices details from the DB";
    }

}
