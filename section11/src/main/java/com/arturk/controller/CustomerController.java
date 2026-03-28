package com.arturk.controller;

import com.arturk.model.dto.LoginRequest;
import com.arturk.model.entity.CustomerEntity;
import com.arturk.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/register")
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{email}")
    public CustomerEntity getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return customerService.login(loginRequest);
    }

    @GetMapping
    public String getCurrentCustomer() {
        return customerService.getCurrentCustomer();
    }
}
