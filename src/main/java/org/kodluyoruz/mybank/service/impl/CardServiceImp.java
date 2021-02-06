package org.kodluyoruz.mybank.service.impl;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.repository.CardRepository;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.service.CardService;
import org.kodluyoruz.mybank.util.NumberEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImp extends NumberEvents implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Card create(CardDto cardDto) {
        return null;
    }

    @Override
    public Page<Card> list(Pageable pageable) {
        return null;
    }

    @Override
    public void addCart(int id, CardDto cardDto) {

        Customer customer = customerRepository.getById(id);
        List<Card> cards = customer.getCards();
        Card card = new Card();

        if (setNewCart(cards,card,customer,cardDto)){
            customerRepository.save(customer);
        }

    }

    public boolean setNewCart(List<Card> cards, Card card, Customer customer, CardDto cardDto){
        for (int i = 0; i < cards.size(); i++) {
            card.setCardNo(createCardNo());
            card.setCardLimit(cardDto.getCardLimit());
            card.setCcv(ccvNo());
            card.setCardType(cardDto.getCardType());

            customer.setCards(cards);
        }
        cards.add(card);
        return true;
    }
}
