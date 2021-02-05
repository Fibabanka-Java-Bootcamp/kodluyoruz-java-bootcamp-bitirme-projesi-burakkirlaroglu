package org.kodluyoruz.mybank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.kodluyoruz.mybank.dto.CardDto;

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

    private String cardNo;

    private String cardType;

    private double cardLimit;

    private double cardDebt;

    @CreationTimestamp
    private LocalDate createdDate;

    private LocalDate expiredDate = LocalDate.of(2022,8,21);

    private int ccv;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Account> accounts;

    @ManyToOne
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
                .cardDebt(this.cardDebt)
                .build();
    }
}

