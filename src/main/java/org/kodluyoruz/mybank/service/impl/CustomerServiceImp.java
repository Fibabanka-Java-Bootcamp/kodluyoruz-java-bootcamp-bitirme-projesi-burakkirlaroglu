package org.kodluyoruz.mybank.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.repository.daoimpl.CustomerDAO;
import org.kodluyoruz.mybank.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImp extends CheckAccountEvents implements CustomerService {


    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public CustomerDto create(CustomerDto customer) {
        Customer c = modelMapper.map(customer, Customer.class);
        c = customerDAO.addCustomer(c);
        customer.setId(c.getId());
        return customer;
    }

    @Override
    @Transactional
    public List<Customer> listAll() {
        return customerDAO.listele();
    }
}
