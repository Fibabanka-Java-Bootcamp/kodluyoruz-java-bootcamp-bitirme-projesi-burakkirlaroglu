package org.kodluyoruz.mybank.controller;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.service.CustomerService;
import org.kodluyoruz.mybank.dto.CustomerDto;
import org.kodluyoruz.mybank.entity.Customer;
import org.kodluyoruz.mybank.transformer.CustomerTransformer;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/customer")
public class CustomerController {


    private final CustomerTransformer customerTransformer;

    private final CustomerService customerService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@RequestBody CustomerDto customerDto){
        return customerService.createCustomer(customerDto);
    }

    @GetMapping(params = {"page","size"})
    public List<CustomerDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        return customerService.listAll(PageRequest.of(page, size)).stream()
                .map(customerTransformer::toCustomerDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public Customer customerById(@PathVariable int id){
        return customerService.getById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Customer updateCustomer(@PathVariable int id, @RequestBody CustomerDto customerDto){
        return customerService.updateCustomer(customerDto, id);
    }

    @DeleteMapping(value = "delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id){
        customerService.deleteCustomer(id);
    }

}
