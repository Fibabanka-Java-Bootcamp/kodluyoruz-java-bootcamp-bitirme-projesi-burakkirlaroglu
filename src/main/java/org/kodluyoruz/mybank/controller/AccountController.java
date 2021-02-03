package org.kodluyoruz.mybank.controller;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.dto.AccountDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.service.AccountService;
import org.kodluyoruz.mybank.transformer.AccountTransformer;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/account")
public class AccountController {

    private final AccountService accountService;

    private final AccountTransformer accountTransformer;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody AccountDto accountDto, @PathVariable int id){
        return accountService.addAccount(accountDto, id);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        return accountService.listPageAccount(PageRequest.of(page, size)).stream()
                .map(accountTransformer::toAccountDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        accountService.deleteAccount(id);
        System.out.println("Account has been deleted! id="+id);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findByid(@PathVariable int id){
        return accountService.getById(id);
    }

}
