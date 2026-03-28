package com.arturk.service;

import com.arturk.model.entity.CustomerEntity;
import com.arturk.model.entity.LoansEntity;
import com.arturk.repository.CustomerRepository;
import com.arturk.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoansService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public List<LoansEntity> getLoanDetailsByEmail(String email) {
        Optional<CustomerEntity> customer = customerRepository.findByEmail(email);

        if(customer.isPresent()) {
            List<LoansEntity> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.get().getId());
            if (!CollectionUtils.isEmpty(loans)) {
                return loans;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }
}
