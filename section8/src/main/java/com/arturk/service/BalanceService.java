package com.arturk.service;

import com.arturk.model.entity.AccountTransactionsEntity;
import com.arturk.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final AccountTransactionsRepository accountTransactionsRepository;

    public List<AccountTransactionsEntity> getBalanceDetailsById(long id) {
        return accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(id);
    }
}
