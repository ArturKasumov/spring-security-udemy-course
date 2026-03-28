package com.arturk.controller;

import com.arturk.model.entity.AccountsEntity;
import com.arturk.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/myAccount")
    public AccountsEntity getAccountDetails(@AuthenticationPrincipal Jwt jwt) {
        return accountService.getAccountDetailsByEmail(jwt.getClaim("email"));
    }

}
