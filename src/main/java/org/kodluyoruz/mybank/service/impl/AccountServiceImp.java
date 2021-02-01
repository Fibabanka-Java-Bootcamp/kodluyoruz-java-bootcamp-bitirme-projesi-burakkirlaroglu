package org.kodluyoruz.mybank.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.service.AccountService;
import org.kodluyoruz.mybank.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImp extends CheckAccountEvents implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public AccountDto saveAccount(AccountDto account) {
        Account a = modelMapper.map(account, Account.class);
        checkedAccountIban(a);
        a = accountRepository.save(a);
        account.setId(a.getId());
        account.setIban(a.getIban());
        return account;
    }

    @Override
    @Transactional
    public TPage<AccountDto> list(Pageable pageable) {
        Page<Account> data = accountRepository.findAll(pageable);
        TPage<AccountDto> response = new TPage<AccountDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), AccountDto.class)));
        return response;
    }

    @Override
    public Page<Account> listPageAccount(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> getById(int id) {
        return accountRepository.findById(id);
    }
}
