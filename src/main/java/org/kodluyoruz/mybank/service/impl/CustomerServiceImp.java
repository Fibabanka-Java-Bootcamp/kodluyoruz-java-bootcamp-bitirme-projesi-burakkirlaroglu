package org.kodluyoruz.mybank.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.service.CustomerService;
import org.kodluyoruz.mybank.transformer.CustomerTransformer;
import org.kodluyoruz.mybank.util.CheckAccountEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CustomerServiceImp extends CheckAccountEvents implements CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerTransformer customerTransformer;


    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerTransformer.customerTransfer(customerDto);
        customerRepository.save(customer);
        customerDto.setId(customer.getId());
        customerDto.setAccounts(customerTransformer.toAccountDtoList(customer.getAccounts()));
        return customerDto;
    }

    @Override
    public Page<Customer> listAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
