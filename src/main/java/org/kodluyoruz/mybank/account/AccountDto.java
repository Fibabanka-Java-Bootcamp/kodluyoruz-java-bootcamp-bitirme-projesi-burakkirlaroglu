package org.kodluyoruz.mybank.account;


import lombok.*;
import org.kodluyoruz.mybank.card.Card;
import org.kodluyoruz.mybank.card.CardDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private int id;

    private double balance;

    private String currency;

    private String accountType;

    private String IBAN;

    private LocalDate created_date = LocalDate.now();

    private List<Card> cards;

    public Account toAccount(){
        return Account.builder()
                .id(this.id)
                .balance(this.balance)
                .currency(this.currency)
                .accountType(this.accountType)
                .IBAN(this.IBAN)
                .created_date(this.created_date)
                .cards(this.cards)
                .build();
    }
}
