package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.service.AccountService;
import org.kodluyoruz.mybank.util.TPage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/v1/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody AccountDto accountDto){
        return accountService.saveAccount(accountDto);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        return accountService.listPageAccount(PageRequest.of(page, size)).stream()
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

    @GetMapping(value = "/pagination")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TPage<AccountDto>> list(Pageable pageable){
        TPage<AccountDto> data = accountService.list(pageable);
        return ResponseEntity.ok(data);
    }

}
