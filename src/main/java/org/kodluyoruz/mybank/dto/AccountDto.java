package org.kodluyoruz.mybank.dto;


import lombok.*;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Customer;

import java.time.LocalDate;

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

    private String iban;

    private LocalDate created_date = LocalDate.now();

  //  private Customer customer;

   // private List<Card> cards;

    public Account toAccount(){
        return Account.builder()
                .id(this.id)
                .balance(this.balance)
                .currency(this.currency)
                .accountType(this.accountType)
                .iban(this.iban)
                .created_date(this.created_date)
                //.cards(this.cards)
                .build();
    }
}
