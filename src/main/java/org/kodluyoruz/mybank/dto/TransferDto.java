package org.kodluyoruz.mybank.dto;

import lombok.*;
import org.kodluyoruz.mybank.entity.Transfer;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {

    private int id;

    private double amount;

    private String currency;

    private String accountType;

    private LocalDate date = LocalDate.now();

    public Transfer toTransfer(){
        return Transfer.builder()
                .id(this.id)
                .amount(this.amount)
                .currency(this.currency)
                .accountType(this.accountType)
                .date(this.date)
                .build();
    }
}
