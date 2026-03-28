package com.arturk.service;

import com.arturk.model.entity.AccountsEntity;
import com.arturk.repository.AccountsRepository;
import com.arturk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    public AccountsEntity getAccountDetailsByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(value -> accountsRepository.findByCustomerId(value.getId())).orElse(null);
    }
}
