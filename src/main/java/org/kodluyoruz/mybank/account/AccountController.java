package org.kodluyoruz.mybank.account;

import org.kodluyoruz.mybank.customer.Customer;
import org.kodluyoruz.mybank.customer.CustomerDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Validated
@RestController
@RequestMapping("/v1/api/customer/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody AccountDto accountDto){
        Account account = new Account();
        return accountService.create(accountDto.toAccount()).toAccountDto();
    }

    @GetMapping(params = {"page","size"})
    public List<AccountDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        return accountService.list(PageRequest.of(page, size)).stream()
                .map(Account::toAccountDto)
                .collect(Collectors.toList());
    }
}
