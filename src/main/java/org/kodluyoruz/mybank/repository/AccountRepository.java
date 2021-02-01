package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Integer> {


    Page<Account> findAll(Pageable pageable);

    void deleteById(Integer id);

    Optional<Account> findById(Integer id);

}
