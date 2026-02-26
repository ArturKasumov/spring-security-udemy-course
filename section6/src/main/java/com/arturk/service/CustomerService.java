package com.arturk.service;

import com.arturk.model.entity.CustomerEntity;
import com.arturk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerEntity getCustomerByEmail(String email){
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Failed load user by username: " + email));
    }

    public CustomerEntity createCustomer(CustomerEntity customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }
}
