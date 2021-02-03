package org.kodluyoruz.mybank.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.repository.CardRepository;
import org.kodluyoruz.mybank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardServiceImp implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card create(Card card) {
        return null;
    }

    @Override
    public Page<Card> list(Pageable pageable) {
        return null;
    }
}
