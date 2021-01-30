package org.kodluyoruz.mybank.customer;

import org.kodluyoruz.mybank.account.Account;
import org.kodluyoruz.mybank.account.AccountDto;
import org.kodluyoruz.mybank.card.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Page<Customer> findAll(Pageable page);

    List<Customer> findByAddress_City(String city);

}
