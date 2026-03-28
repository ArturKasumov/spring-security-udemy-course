package com.arturk.service;

import com.arturk.model.entity.CardsEntity;
import com.arturk.model.entity.CustomerEntity;
import com.arturk.repository.CardsRepository;
import com.arturk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardsService {

    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;

    public List<CardsEntity> getCardDetailsByEmail(String email) {
        Optional<CustomerEntity> customer = customerRepository.findByEmail(email);

        if(customer.isPresent()) {
            List<CardsEntity> cards = cardsRepository.findByCustomerId(customer.get().getId());
            if (!CollectionUtils.isEmpty(cards)) {
                return cards;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }
}
