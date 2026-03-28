package com.arturk.controller;

import com.arturk.model.entity.CardsEntity;
import com.arturk.repository.CardsRepository;
import com.arturk.service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsService cardsService;

    @GetMapping("/myCards")
    public List<CardsEntity> getCardDetails(@RequestParam long id) {
        return cardsService.getCardDetailsById(id);
    }

}
