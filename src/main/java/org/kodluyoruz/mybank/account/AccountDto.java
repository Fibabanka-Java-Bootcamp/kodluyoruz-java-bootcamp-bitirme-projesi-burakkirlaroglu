package org.kodluyoruz.mybank.account;


import lombok.*;

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

    private String IBAN;

    private LocalDate created_date = LocalDate.now();

    public Account toAccount(){
        return Account.builder()
                .id(this.id)
                .balance(this.balance)
                .currency(this.currency)
                .accountType(this.accountType)
                .IBAN(this.IBAN)
                .created_date(this.created_date)
                .build();
    }
}
