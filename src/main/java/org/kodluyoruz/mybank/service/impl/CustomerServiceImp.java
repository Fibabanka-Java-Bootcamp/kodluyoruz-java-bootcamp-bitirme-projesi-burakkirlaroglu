package org.kodluyoruz.mybank.service.impl;

import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Card;
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
        customerDto.setCreatedDate(customer.getCreatedDate());
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

        Customer customer = customerRepository.getById(customerId);

        customer.setFullName(customerDto.getFullName());
        customer.setPassword(customerDto.getPassword());
        customer.setDescription(customerDto.getDescription());
        customer.setEmail(customerDto.getEmail());
        customer.setTC(customerDto.getTC());
        customer.setPhone(customerDto.getPhone());

        customerRepository.save(customer);

        return customer;

    }

    @Override
    public void deleteCustomer(int id){
        Customer customer = customerRepository.getById(id);

        if (accountBalanceCheck(customer) & cardDebtCheck(customer)){
            customerRepository.deleteById(id);
        }

    }

    private boolean accountBalanceCheck(Customer customer){
        for (int i = 0; i < customer.getAccounts().size(); i++) {
            Account account = customer.getAccounts().get(i);
            if (account.getBalance() > 0){
                throw new NullPointerException(customer.getFullName() +  "Kullanıcısının Bakiyesinde para var");
            }
        }
        return true;
    }

    private boolean cardDebtCheck(Customer customer){
        for (int i = 0; i < customer.getCards().size(); i++) {
            Card card = customer.getCards().get(i);
            if (card.getCardDebt() > 0){
                throw new NullPointerException(customer.getFullName() +  "Kullanıcısının Borcu var");
            }
        }
        return true;
    }


}
