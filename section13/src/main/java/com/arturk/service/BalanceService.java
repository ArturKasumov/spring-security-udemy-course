package com.arturk.service;

import com.arturk.model.entity.AccountTransactionsEntity;
import com.arturk.model.entity.CustomerEntity;
import com.arturk.repository.AccountTransactionsRepository;
import com.arturk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    public List<AccountTransactionsEntity> getBalanceDetailsByEmail(String email) {
        Optional<CustomerEntity> customer = customerRepository.findByEmail(email);

        if (customer.isPresent()) {
            List<AccountTransactionsEntity> accountTransactions = accountTransactionsRepository
                    .findByCustomerIdOrderByTransactionDtDesc(customer.get().getId());
            if (!CollectionUtils.isEmpty(accountTransactions)) {
                return accountTransactions;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }
}
