package org.kodluyoruz.mybank.dto;

import lombok.*;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Address;
import org.kodluyoruz.mybank.entity.Card;
import org.kodluyoruz.mybank.entity.Customer;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private int id;

    private String fullName;

    private String TC;

    private String password;

    private String description;

    private String email;

    private String phone;

    private LocalDate created_date = LocalDate.now();

    private Address address;

    private List<Account> accounts;

    private List<Card> cards;

    public Customer toCustomer(){
        return Customer.builder()
                .id(this.id)
                .fullName(this.fullName)
                .TC(this.TC)
                .password(this.password)
                .description(this.description)
                .email(this.email)
                .phone(this.phone)
                .created_date(this.created_date)
                .address(this.address)
                .cards(this.cards)
                .accounts(this.accounts)
                .build();
    }
}
