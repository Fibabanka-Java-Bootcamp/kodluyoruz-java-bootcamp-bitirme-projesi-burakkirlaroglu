package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface AccountService {

    Account addAccount(AccountDto accountDto, int customerId);

    Page<Account> listPageAccount(Pageable pageable);

    void deleteAccount(int id);

    Account getById(int id);

}
