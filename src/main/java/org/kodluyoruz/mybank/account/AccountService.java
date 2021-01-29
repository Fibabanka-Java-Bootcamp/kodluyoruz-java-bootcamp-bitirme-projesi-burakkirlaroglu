package org.kodluyoruz.mybank.account;

import org.kodluyoruz.mybank.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account account){
            return accountRepository.save(account);
    }

    public Page<Account> list(Pageable pageable){
        return accountRepository.findAll(pageable);
    }
}
