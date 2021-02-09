package org.kodluyoruz.mybank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.kodluyoruz.mybank.dto.CardDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    private double amount;

    private String cardType;

    private String cardPassword;

    private double cardLimit;

    private String expenses;

    private double cardDebt;

    @CreationTimestamp
    private LocalDate createdDate;

    private LocalDate expiredDate = LocalDate.of(2022,8,21);

    private int ccv;

    @ManyToMany(mappedBy = "cards")
    private List<Account> accounts;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

}

