package com.arturk.controller;

import com.arturk.model.entity.CustomerEntity;
import com.arturk.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/register")
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {
        return customerService.createCustomer(customer);
    }

    @SneakyThrows
    @GetMapping
    public String getCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = new HashMap<>();
        map.put("username", authentication.getName());
        map.put("password", authentication.getCredentials() != null ? authentication.getCredentials().toString() : "");
        map.put("role", authentication.getAuthorities() != null ? authentication.getAuthorities().toString() : "");
        return new ObjectMapper().writeValueAsString(map);
    }
}
