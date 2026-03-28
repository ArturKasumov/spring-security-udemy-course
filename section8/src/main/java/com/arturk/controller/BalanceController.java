package com.arturk.controller;

import com.arturk.model.entity.AccountTransactionsEntity;
import com.arturk.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/myBalance")
    public List<AccountTransactionsEntity> getBalanceDetails(@RequestParam long id) {
       return balanceService.getBalanceDetailsById(id);
    }
}
