package com.arturk.service;

import com.arturk.model.entity.AccountsEntity;
import com.arturk.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountsRepository accountsRepository;

    public AccountsEntity getAccountDetailsById(long id) {
        return accountsRepository.findByCustomerId(id);
    }
}
