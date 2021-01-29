package org.kodluyoruz.mybank.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.card.Card;
import org.kodluyoruz.mybank.customer.Customer;
import org.kodluyoruz.mybank.transfer.Transfer;

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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private double balance;

    private String currency;

    private String accountType;

    private String IBAN;

    private LocalDate created_date = LocalDate.now();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"))
    private List<Card> cards;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "transfer_id", referencedColumnName = "id"))
    private List<Transfer> transfers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    public AccountDto toAccountDto(){
        return AccountDto.builder()
                .id(this.id)
                .balance(this.balance)
                .currency(this.currency)
                .accountType(this.accountType)
                .IBAN(this.IBAN)
                .created_date(this.created_date)
                .cards(this.cards)
                .build();
    }

}
