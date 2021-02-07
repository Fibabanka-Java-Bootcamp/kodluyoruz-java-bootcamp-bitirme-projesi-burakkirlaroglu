package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface CardService {

    Page<Card> list(Pageable pageable);

    void addCart(int id, CardDto cardDto);

    HashMap<String,Double> findCardDept(int id);
}
