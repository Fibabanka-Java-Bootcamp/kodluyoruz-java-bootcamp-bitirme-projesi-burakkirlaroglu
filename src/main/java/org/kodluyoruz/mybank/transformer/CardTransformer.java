package org.kodluyoruz.mybank.transformer;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.util.NumberEvents;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CardTransformer extends NumberEvents {

    public Card cardTransfer(CardDto cardDto){
        Card card = new Card();
        toCreateCard(card,cardDto);
        return card;
    }


    private void toCreateCard(Card card, CardDto cardDto){
        card.setCardNo(createCardNo());
        card.setCcv(ccvNo());
        card.setCardType(cardDto.getCardType());
        card.setCardDebt(cardDto.getCardDebt());
        card.setCardLimit(cardDto.getCardLimit());
        card.setExpenses(cardDto.getExpenses());
        card.setAmount(cardDto.getAmount());
        card.setCreatedDate(LocalDate.now());
    }


//    public Card toCard(Card card){
//        return Card.builder()
//                .id(card.getId())
//                .cardNo(card.getCardNo())
//                .cardType(card.getCardType())
//                .cardLimit(card.getCardLimit())
//                .expiredDate(card.getExpiredDate())
//                .ccv(card.getCcv())
//                .createdDate(card.getCreatedDate())
//                .cardDebt(card.getCardDebt())
//                .amount(card.getAmount())
//                .expenses(card.getExpenses())
//                .build();
//    }

    public CardDto toCardDto(Card card){
        return CardDto.builder()
                .id(card.getId())
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .cardLimit(card.getCardLimit())
                .expiredDate(card.getExpiredDate())
                .ccv(card.getCcv())
                .createdDate(card.getCreatedDate())
                .cardDebt(card.getCardDebt())
                .amount(card.getAmount())
                .expenses(card.getExpenses())
                .build();
    }
}
