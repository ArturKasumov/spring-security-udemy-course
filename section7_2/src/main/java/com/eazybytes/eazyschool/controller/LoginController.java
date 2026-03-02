package com.eazybytes.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String displayLoginPage(@RequestParam(name = "error", required = false) Boolean isError,
                                   @RequestParam(name = "logout", required = false) Boolean isLoggedOut,
                                   Model model) {
        if(Boolean.TRUE.equals(isError)) {
            model.addAttribute("errorMessage", "Incorrect username or password.");
        }
        if(Boolean.TRUE.equals(isLoggedOut)) {
            model.addAttribute("logoutMessage", "You have been logged out.");
        }
        return "login.html";
    }

}