package org.kodluyoruz.mybank.service.impl;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.repository.CardRepository;
import org.kodluyoruz.mybank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImp implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card create(CardDto cardDto) {
        return null;
    }

    @Override
    public Page<Card> list(Pageable pageable) {
        return null;
    }
}
