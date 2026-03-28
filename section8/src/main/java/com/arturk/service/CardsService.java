package com.arturk.service;

import com.arturk.model.entity.CardsEntity;
import com.arturk.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardsService {

    private final CardsRepository cardsRepository;

    public List<CardsEntity> getCardDetailsById(long id) {
        return cardsRepository.findByCustomerId(id);
    }
}
