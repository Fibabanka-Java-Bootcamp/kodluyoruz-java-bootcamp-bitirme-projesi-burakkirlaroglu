package org.kodluyoruz.mybank.service.impl;

import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.service.AccountService;
import org.kodluyoruz.mybank.util.NumberEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImp extends NumberEvents implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Account> listPageAccount(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account getById(int id) {
        return accountRepository.getById(id);
    }
}
