package org.kodluyoruz.mybank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.service.impl.CheckAccountEvents;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private double balance;

    private String currency;

    private String accountType;

    private String iban;

    private LocalDate created_date = LocalDate.now();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Card> cards;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "transfer_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Transfer> transfers;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public AccountDto toAccountDto(){
        return AccountDto.builder()
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
