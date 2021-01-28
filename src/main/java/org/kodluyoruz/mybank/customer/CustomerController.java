package org.kodluyoruz.mybank.customer;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        Customer customer = new Customer();
        return customerService.create(customerDto.toCustomer()).toCustomerDto();
    }

    @GetMapping(params = {"page","size"})
    public List<CustomerDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        return customerService.list(PageRequest.of(page, size)).stream()
                .map(Customer::toCustomerDto)
                .collect(Collectors.toList());
    }
}
