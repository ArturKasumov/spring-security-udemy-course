package com.arturk.controller;

import com.arturk.model.entity.LoansEntity;
import com.arturk.service.LoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoansService loansService;

    @GetMapping("/myLoans")
    public List<LoansEntity> getLoanDetails(@AuthenticationPrincipal Jwt jwt) {
        return loansService.getLoanDetailsByEmail(jwt.getClaim("email"));
    }

}
