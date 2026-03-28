package com.arturk.service;

import com.arturk.model.dto.LoginRequest;
import com.arturk.model.entity.CustomerEntity;
import com.arturk.repository.CustomerRepository;
import com.arturk.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public CustomerEntity getCustomerByEmail(String email){
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Failed load user by username: " + email));
    }

    public CustomerEntity createCustomer(CustomerEntity customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException(
                    String.format("User with username: %s already exists", customer.getEmail())
            );
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @SneakyThrows
    public String getCurrentCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = new HashMap<>();
        map.put("username", authentication.getName());
        map.put("password", authentication.getCredentials() != null ? authentication.getCredentials().toString() : "");
        map.put("role", authentication.getAuthorities() != null ? authentication.getAuthorities().toString() : "");
        return new ObjectMapper().writeValueAsString(map);
    }

    public String login(LoginRequest loginRequest) {
        Authentication authenticated = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                )
        );
        return jwtUtil.generateToken(authenticated);
    }
}
