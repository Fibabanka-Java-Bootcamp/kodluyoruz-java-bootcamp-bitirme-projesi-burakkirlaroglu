package org.kodluyoruz.mybank.service.impl;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.repository.CardRepository;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.service.CardService;
import org.kodluyoruz.mybank.transformer.CardTransformer;
import org.kodluyoruz.mybank.util.NumberEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardServiceImp extends NumberEvents implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CardTransformer cardTransformer;

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


    @Override
    public HashMap<String,Double> findCardDept(int id) {
        Customer customer = customerRepository.getById(id);
        List<Card> cards = customer.getCards();

        HashMap<String, Double> myJson = new HashMap<>();

        for (Card card : cards) {
            if (card.getCardType().equals("CREDIT")){
                double debtAmount = card.getCardDebt();
                myJson.put(card.getCardType()+" CARD DEPT",debtAmount);
            }
        }
        return myJson;
    }

    public Card sendMoneyForShop(int id, CardDto cardDto){

        Customer customer = customerRepository.getById(id);

        List<Card> cards = customer.getCards();

        for (int i = 0; i < cards.size(); i++) {
            Card card = customer.getCards().get(i);
            double amount = cardDto.getAmount();
            int ccv = cardDto.getCcv();
            String cardNo = cardDto.getCardNo();
            String expenses = cardDto.getExpenses();
            toShoppingFromCard(card, amount, cardNo, ccv, expenses);
        }

        return cards.get(0);

    }

    private void toShoppingFromCard(Card card, double amount, String cardNo, int ccv, String expenses){

        if (card.getCardNo().equals(cardNo) & card.getCcv() == ccv){
            card.setAmount(amount);
            if (card.getCardType().equals("CREDIT")){
                card.setCardDebt(card.getCardDebt() + amount);
            }
            card.setExpenses(expenses);
            card.setCardLimit(card.getCardLimit() - amount);
            cardRepository.save(card);
        }else{
            System.out.println("yanlış kart no veya ccv");
        }
    }

}
