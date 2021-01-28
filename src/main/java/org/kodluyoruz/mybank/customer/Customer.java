package org.kodluyoruz.mybank.customer;

import lombok.*;
import org.kodluyoruz.mybank.account.Account;
import org.kodluyoruz.mybank.address.Address;
import org.kodluyoruz.mybank.card.Card;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String fullName;

    private String TC;

    private String password;

    private String description;

    private String email;

    private String phone;

    private LocalDate created_date = LocalDate.now();

    @OneToMany(mappedBy = "customer")
    private List<Card> cards;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer")
    private Address address;

    public CustomerDto toCustomerDto(){
        return CustomerDto.builder()
                .id(this.id)
                .fullName(this.fullName)
                .TC(this.TC)
                .password(this.password)
                .description(this.description)
                .email(this.email)
                .phone(this.phone)
                .created_date(this.created_date)
                .address(this.address)
                .build();
    }
}
