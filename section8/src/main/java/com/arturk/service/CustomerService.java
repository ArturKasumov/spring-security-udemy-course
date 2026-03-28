package com.arturk.service;

import com.arturk.model.entity.CustomerEntity;
import com.arturk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerEntity getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Failed load user by username: " + email));
    }

    public CustomerEntity getUserDetailsAfterLogin(Authentication authentication) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }

    public CustomerEntity registerUser(CustomerEntity customer) {
        String hashPwd = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashPwd);
        customer.setCreateDt(new Date(System.currentTimeMillis()));
        return customerRepository.save(customer);
    }
}
