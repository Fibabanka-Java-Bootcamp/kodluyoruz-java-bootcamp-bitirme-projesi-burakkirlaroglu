package org.kodluyoruz.mybank.account;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        return accountService.list(PageRequest.of(page, size)).stream()
                .map(Account::toAccountDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        accountService.deleteAccount(id);
        System.out.println("Account has been deleted! id="+id);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Account> findByid(@PathVariable int id){
        return accountService.getById(id);
    }

//    @GetMapping(value = "/cust/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Account findCustomerAccount(@PathVariable int id){
//        return accountService.findAccountCustomerWithId(id);
//    }

}
