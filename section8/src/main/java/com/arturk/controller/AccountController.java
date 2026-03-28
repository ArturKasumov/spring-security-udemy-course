package com.arturk.controller;

import com.arturk.model.entity.AccountsEntity;
import com.arturk.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/myAccount")
    public AccountsEntity getAccountDetails(@RequestParam long id) {
        return accountService.getAccountDetailsById(id);
    }

}
