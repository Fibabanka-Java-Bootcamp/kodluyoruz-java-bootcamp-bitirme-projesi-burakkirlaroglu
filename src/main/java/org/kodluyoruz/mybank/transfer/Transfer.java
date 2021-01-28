package org.kodluyoruz.mybank.transfer;

import lombok.*;
import org.kodluyoruz.mybank.account.Account;

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
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private double amount;

    private String currency;

    private String accountType;

    private LocalDate date = LocalDate.now();

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "transfer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    private List<Account> accounts;

    public TransferDto toTransferDto(){
        return TransferDto.builder()
                .id(this.id)
                .amount(this.amount)
                .currency(this.currency)
                .accountType(this.accountType)
                .date(this.date)
                .build();
    }

}
