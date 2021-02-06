package org.kodluyoruz.mybank.controller;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
@RequestMapping("/v1/api/account")
public class AccountController {

    @Autowired
    private final AccountService accountService;


    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        return accountService.listPageAccount(PageRequest.of(page, size)).stream()
                .map(Account::toAccountDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        accountService.deleteAccount(id);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findByid(@PathVariable int id){
        return accountService.getById(id);
    }

    @PutMapping(value = "/custno/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccount(@PathVariable int id, @RequestBody AccountDto accountDto){
        accountService.addAccount(id, accountDto);
    }

}
