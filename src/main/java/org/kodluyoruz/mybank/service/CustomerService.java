package org.kodluyoruz.mybank.service;

import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;

import java.util.List;


public interface CustomerService {

    CustomerDto create(CustomerDto customer);

    List<Customer> listAll();
}
