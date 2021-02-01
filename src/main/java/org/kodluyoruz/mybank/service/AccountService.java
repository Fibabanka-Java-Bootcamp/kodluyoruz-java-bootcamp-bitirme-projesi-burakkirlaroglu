package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface AccountService {

    AccountDto saveAccount(AccountDto account);

    TPage<AccountDto> list(Pageable pageable);

    Page<Account> listPageAccount(Pageable pageable);

    void deleteAccount(int id);

    Optional<Account> getById(int id);

}
