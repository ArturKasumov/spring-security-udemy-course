package com.arturk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping(value = "/myCards")
    public String getCardsDetails() {
        return "Here are the cards details from the DB";
    }

}
