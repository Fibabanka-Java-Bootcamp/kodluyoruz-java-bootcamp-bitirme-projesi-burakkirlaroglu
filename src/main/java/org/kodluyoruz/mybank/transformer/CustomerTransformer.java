package org.kodluyoruz.mybank.transformer;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.util.CheckAccountEvents;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerTransformer extends CheckAccountEvents {

    public Customer customerTransfer(CustomerDto customerDto) {

        List<Account> accounts = new ArrayList<>();
        accountDtoCreate(accounts, customerDto);
        Customer customer = new Customer();
        customerCrete(accounts,customerDto,customer);
        return customer;

    }


    private void customerCrete(List<Account> accounts, CustomerDto customerDto, Customer customer ){
        customer.setAccounts(accounts);
        customer.setDescription(customerDto.getDescription());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setFullName(customerDto.getFullName());
        customer.setTC(customerDto.getTC());
        customer.setPassword(customerDto.getPassword());
        customer.setPhone(customerDto.getPhone());
    }

    private void accountDtoCreate(List<Account> accounts, CustomerDto customerDto) {

        for (int i = 0; i < customerDto.getAccounts().size(); i++) {

            AccountDto accountDto = customerDto.getAccounts().get(i);

            Account account = new Account();
            account.setAccountType(accountDto.getAccountType());
            account.setCurrency(accountDto.getCurrency());
            account.setBalance(accountDto.getBalance());
            account.setIban(setAccountIban(account));
            accounts.add(account);
        }
    }

    public CustomerDto toCustomerDto(Customer customer){

        return CustomerDto.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .TC(customer.getTC())
                .password(customer.getPassword())
                .description(customer.getDescription())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .created_date(customer.getCreated_date())
                .address(customer.getAddress())
                .cards(customer.getCards())
                .accounts(toAccountDtoList(customer.getAccounts()))
                .build();
    }

    private AccountDto toAccountDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .accountType(account.getAccountType())
                .iban(account.getIban())
                .createdDate(LocalDate.now())
                .build();
    }

    public List<AccountDto> toAccountDtoList(List<Account> accounts){
        List<AccountDto> accountDtos = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            accountDtos.add(toAccountDto(accounts.get(i)));
        }
        return accountDtos;
    }

}
