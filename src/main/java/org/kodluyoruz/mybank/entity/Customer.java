package org.kodluyoruz.mybank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.dto.CustomerDto;

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String TC;

    private String password;

    private String description;

    private String email;

    private String phone;

    private LocalDate created_date = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Card> cards;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Account> accounts;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
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
                .cards(this.cards)
                .accounts(this.accounts)
                .build();
    }
}
