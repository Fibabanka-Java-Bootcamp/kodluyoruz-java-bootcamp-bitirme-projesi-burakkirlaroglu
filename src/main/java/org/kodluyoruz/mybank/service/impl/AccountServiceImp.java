package org.kodluyoruz.mybank.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.service.AccountService;
import org.kodluyoruz.mybank.transformer.AccountTransformer;
import org.kodluyoruz.mybank.util.CheckAccountEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImp extends CheckAccountEvents implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransformer accountTransformer;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public AccountDto addAccount(AccountDto accountDto, int customerId) {

        Customer customer = customerRepository.getById(customerId);

        Account account = accountTransformer.AccountTransfer(accountDto);
        account.setCustomer(customer);
        setAccountIban(account);
        accountRepository.save(account);
        accountDto.setId(account.getId());
        accountDto.setIban(account.getIban());
        accountDto.setCreatedDate(account.getCreatedDate());
        accountDto.setCustomerDto(accountTransformer.toCustomerDtos(account.getCustomer()));
        return accountDto;
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
    public Account getById(int id) {
        return accountRepository.getById(id);
    }
}
