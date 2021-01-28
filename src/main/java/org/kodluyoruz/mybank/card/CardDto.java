package org.kodluyoruz.mybank.card;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private int id;

    private int cardNo;

    private String cardType;

    private double carLimit;

    private LocalDate expiredDate;

    private int ccv;

    private LocalDate createdDate = LocalDate.now();

    public Card toCard(){
        return Card.builder()
                .id(this.id)
                .cardNo(this.cardNo)
                .cardType(this.cardType)
                .carLimit(this.carLimit)
                .expiredDate(this.expiredDate)
                .ccv(this.ccv)
                .createdDate(this.createdDate)
                .build();
    }
}
