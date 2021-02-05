package org.kodluyoruz.mybank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.kodluyoruz.mybank.dto.AccountDto;

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
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private double balance;

    private String currency;

    private String accountType;

    private String iban;

    @CreationTimestamp
    private LocalDate createdDate;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Card> cards;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "transfer_id", referencedColumnName = "id"))
    private List<Transfer> transfers;

    @ManyToOne(cascade =  CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Customer customer;

    public AccountDto toAccountDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .accountType(account.getAccountType())
                .iban(account.getIban())
                .createdDate(LocalDate.now())
                .build();
    }





}
