package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

    public Card create(Card card);

    public Page<Card> list(Pageable pageable);
}
