package com.arturk.controller;

import com.arturk.model.entity.CustomerEntity;
import com.arturk.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/register")
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {
        return customerService.createCustomer(customer);
    }
}
