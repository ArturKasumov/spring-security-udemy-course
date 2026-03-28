package com.arturk.controller;

import com.arturk.model.entity.LoansEntity;
import com.arturk.repository.LoanRepository;
import com.arturk.service.LoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoansService loansService;

    @GetMapping("/myLoans")
    public List<LoansEntity> getLoanDetails(@RequestParam long id) {
        return loansService.getLoanDetailsById(id);
    }

}
