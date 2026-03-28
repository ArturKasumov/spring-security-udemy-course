package com.arturk.controller;

import com.arturk.model.entity.AccountTransactionsEntity;
import com.arturk.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/myBalance")
    public List<AccountTransactionsEntity> getBalanceDetails(@AuthenticationPrincipal Jwt jwt) {
        return balanceService.getBalanceDetailsByEmail(jwt.getClaim("email"));
    }
}
