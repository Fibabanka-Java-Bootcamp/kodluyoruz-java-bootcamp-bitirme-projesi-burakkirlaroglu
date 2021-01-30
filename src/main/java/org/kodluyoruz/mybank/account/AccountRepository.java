package org.kodluyoruz.mybank.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AccountRepository extends CrudRepository<Account, Integer> {

    Page<Account> findAll(Pageable page);
    void deleteById(Integer id);
    Optional<Account> findById(Integer id);

    //Account findAccountByCustomerId(Integer id);

}
