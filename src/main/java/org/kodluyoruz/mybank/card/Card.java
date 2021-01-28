package org.kodluyoruz.mybank.card;

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

    private double carLimit;

    private LocalDate expiredDate;

    private int ccv;

    private LocalDate createdDate = LocalDate.now();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    private List<Account> accountList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public CardDto toCardDto(){
        return CardDto.builder()
                .id(this.id)
                .cardNo(this.cardNo)
                .cardType(this.cardType)
                .carLimit(this.carLimit)
                .expiredDate(this.expiredDate)
                .ccv(this.ccv)
                .createdDate(this.createdDate)
                .build();
    }
}

