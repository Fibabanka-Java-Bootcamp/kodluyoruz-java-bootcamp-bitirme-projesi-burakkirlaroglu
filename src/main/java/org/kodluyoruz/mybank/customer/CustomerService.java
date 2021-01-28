package org.kodluyoruz.mybank.customer;

import org.kodluyoruz.mybank.account.Account;
import org.kodluyoruz.mybank.card.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }

    public Page<Customer> list(PageRequest page){
        return customerRepository.findAll(page);
    }

    public List<Customer> listByAccount(Account account){
        return customerRepository.findCustomerByAccounts(account);
    }

    public List<Customer> listByCard(Card card){
        return customerRepository.findCustomerByCards(card);
    }
}