package org.kodluyoruz.mybank.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.account.Account;
import org.kodluyoruz.mybank.customer.Customer;

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
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private int cardNo;

    private String cardType;

    private double cardLimit;

    private LocalDate expiredDate = LocalDate.of(2022,8,21);

    private int ccv;

    private LocalDate createdDate = LocalDate.now();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Account> accounts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    public CardDto toCardDto(){
        return CardDto.builder()
                .id(this.id)
                .cardNo(this.cardNo)
                .cardType(this.cardType)
                .cardLimit(this.cardLimit)
                .expiredDate(this.expiredDate)
                .ccv(this.ccv)
                .createdDate(this.createdDate)
                .accounts(this.accounts)
                .build();
    }
}

