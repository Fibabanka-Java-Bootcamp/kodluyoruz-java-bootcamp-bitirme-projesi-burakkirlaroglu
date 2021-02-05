package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

    Card create(CardDto cardDto);

    Page<Card> list(Pageable pageable);
}
