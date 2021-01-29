package org.kodluyoruz.mybank.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account, Integer> {


    Page<Account> findAll(Pageable page);

}
