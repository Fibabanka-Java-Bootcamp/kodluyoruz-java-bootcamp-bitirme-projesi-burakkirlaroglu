package org.kodluyoruz.mybank.transformer;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AccountTransformer {


    public Account AccountTransfer(AccountDto accountDto){
        Account account = new Account();
        accountCreate(account, accountDto);
        return account;
    }

    private void accountCreate(Account account,AccountDto accountDto){
        account.setAccountType(accountDto.getAccountType());
        account.setCurrency(accountDto.getCurrency());
        account.setIban(accountDto.getIban());
        account.setBalance(accountDto.getBalance());
    }

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

    public Account toAccount(AccountDto account){
        return Account.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .accountType(account.getAccountType())
                .iban(account.getIban())
                .createdDate(LocalDate.now())
                .build();
    }


}
