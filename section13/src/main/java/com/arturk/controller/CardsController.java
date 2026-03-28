package com.arturk.controller;

import com.arturk.model.entity.CardsEntity;
import com.arturk.service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsService cardsService;

    @GetMapping("/myCards")
    public List<CardsEntity> getCardDetails(@AuthenticationPrincipal Jwt jwt) {
        return cardsService.getCardDetailsByEmail(jwt.getClaim("email"));
    }

}
