package org.kodluyoruz.mybank.card;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card create(Card card){
        return cardRepository.save(card);
    }

    public Page<Card> list(Pageable pageable){
        return cardRepository.findAll(pageable);
    }
}
