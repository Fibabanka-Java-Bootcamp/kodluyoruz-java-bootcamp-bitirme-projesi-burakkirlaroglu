package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.service.CustomerService;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/v1/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@RequestBody CustomerDto customerDto){
        return customerService.create(customerDto);
    }

//    @GetMapping(params = {"page","size"})
//    public List<CustomerDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
//        return customerService.list(PageRequest.of(page, size)).stream()
//                .map(Customer::toCustomerDto)
//                .collect(Collectors.toList());
//    }


    @GetMapping
    public List<Customer> getCustomers (){
        return customerService.listAll();
    }

}
