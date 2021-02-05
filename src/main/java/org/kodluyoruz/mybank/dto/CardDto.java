package org.kodluyoruz.mybank.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Card;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private int id;

    private String cardNo;

    private String cardType;

    private double cardLimit;

    private double cardDebt;

    private LocalDate expiredDate;

    private int ccv;

    private LocalDate createdDate;

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
                .cardDebt(this.cardDebt)
                .build();
    }
}
