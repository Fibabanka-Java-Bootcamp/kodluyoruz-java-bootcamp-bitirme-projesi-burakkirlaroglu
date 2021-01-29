package org.kodluyoruz.mybank.card;

import lombok.*;
import org.kodluyoruz.mybank.account.Account;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private int id;

    private int cardNo;

    private String cardType;

    private double cardLimit;

    private LocalDate expiredDate;

    private int ccv;

    private LocalDate createdDate = LocalDate.now();

    private List<Account> accounts;

    public Card toCard(){
        return Card.builder()
                .id(this.id)
                .cardNo(this.cardNo)
                .cardType(this.cardType)
                .cardLimit(this.cardLimit)
                .expiredDate(this.expiredDate)
                .ccv(this.ccv)
                .createdDate(this.createdDate)
                .accounts(this.accounts)
                .build();
    }
}
