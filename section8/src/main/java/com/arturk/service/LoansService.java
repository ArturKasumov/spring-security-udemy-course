package com.arturk.service;

import com.arturk.model.entity.LoansEntity;
import com.arturk.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoansService {

    private final LoanRepository loanRepository;

    public List<LoansEntity> getLoanDetailsById(long id) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
