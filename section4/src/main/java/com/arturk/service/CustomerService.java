package com.arturk.service;

import com.arturk.model.entity.CustomerEntity;
import com.arturk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerEntity getCustomerByEmail(String email){
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Failed load user by username: " + email));
    }
}
