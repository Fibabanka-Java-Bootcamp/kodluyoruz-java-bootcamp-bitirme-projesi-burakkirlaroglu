package org.kodluyoruz.mybank.service.impl;

import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.repository.CustomerRepository;
import org.kodluyoruz.mybank.service.CustomerService;
import org.kodluyoruz.mybank.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerServiceImp implements CustomerService {


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
        customerDto.setCards(customerTransformer.toCardDtoList(customer.getCards()));
        return customerDto;
    }

    @Override
    public Page<Customer> listAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    @Override
    @Transactional
    public Customer updateCustomer(CustomerDto customerDto, int customerId) {

        customerId = customerDto.getId();

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFullName(customerDto.getFullName());
        customer.setPassword(customerDto.getPassword());
        customer.setDescription(customerDto.getDescription());
        customer.setEmail(customerDto.getEmail());
        customer.setTC(customerDto.getTC());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());

        customerRepository.save(customer);

        return customer;

    }

    public void deleteCustomer(int id){
        customerRepository.deleteById(id);
    }


}
