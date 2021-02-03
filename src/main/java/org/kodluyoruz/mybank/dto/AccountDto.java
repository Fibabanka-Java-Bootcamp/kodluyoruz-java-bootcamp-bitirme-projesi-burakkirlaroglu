package org.kodluyoruz.mybank.dto;


import lombok.*;
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

    private LocalDate createdDate;

    private CustomerDto customerDto;

}
