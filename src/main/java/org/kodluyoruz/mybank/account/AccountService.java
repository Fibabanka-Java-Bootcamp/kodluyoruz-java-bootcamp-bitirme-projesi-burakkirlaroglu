package org.kodluyoruz.mybank.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService extends CheckAccountEvents {

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account account){
        checkedAccountIban(account);
        return accountRepository.save(account);
    }

    public Page<Account> list(Pageable pageable){
        return accountRepository.findAll(pageable);
    }

    public void deleteAccount(int id){
            accountRepository.deleteById(id);
    }

    public Optional<Account> getById(int id){
        return accountRepository.findById(id);
    }

//    public Account findAccountCustomerWithId(int id){
//        return accountRepository.findAccountByCustomerId(id);
//    }

}
