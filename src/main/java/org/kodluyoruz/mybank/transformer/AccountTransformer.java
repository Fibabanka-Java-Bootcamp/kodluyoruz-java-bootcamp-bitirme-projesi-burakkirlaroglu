package org.kodluyoruz.mybank.transformer;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AccountTransformer {


    public Account AccountTransfer(AccountDto accountDto){

        CustomerDto customerDto = accountDto.getCustomerDto();

        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setPassword(customerDto.getPassword());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setTC(customerDto.getTC());
        customer.setFullName(customerDto.getFullName());
        customer.setDescription(customerDto.getDescription());
        customer.setAddress(customerDto.getAddress());
        customer.setCards(customerDto.getCards());

        Account account = new Account();
        account.setCustomer(customer);
        account.setAccountType(accountDto.getAccountType());
        account.setCurrency(accountDto.getCurrency());
        account.setIban(accountDto.getIban());
        account.setBalance(accountDto.getBalance());
        account.setTransfers(account.getTransfers());
        return account;

    }

    private CustomerDto toCustomerDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .address(customer.getAddress())
                .created_date(customer.getCreated_date())
                .description(customer.getDescription())
                .TC(customer.getTC())
                .password(customer.getPassword())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .fullName(customer.getFullName())
                .build();
    }

    public AccountDto toAccountDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .accountType(account.getAccountType())
                .iban(account.getIban())
                .createdDate(LocalDate.now())
                .customerDto(toCustomerDtos(account.getCustomer()))
                .build();
    }

    public CustomerDto toCustomerDtos(Customer customer){
        return toCustomerDto(customer);
    }


}
