package com.arturk.controller;

import com.arturk.model.entity.CustomerEntity;
import com.arturk.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public CustomerEntity registerUser(@RequestBody CustomerEntity customer) {
        return customerService.registerUser(customer);
    }

    @RequestMapping("/user")
    public CustomerEntity getUserDetailsAfterLogin(Authentication authentication) {
        return customerService.getUserDetailsAfterLogin(authentication);
    }

}
