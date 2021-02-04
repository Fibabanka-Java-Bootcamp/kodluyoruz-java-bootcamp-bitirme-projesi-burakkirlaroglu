package org.kodluyoruz.mybank.controller;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.dto.TransferDto;
import org.kodluyoruz.mybank.util.ExchangeRates;
import org.kodluyoruz.mybank.entity.Transfer;
import org.kodluyoruz.mybank.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/transfer")
public class TransferController {


    private final TransferService transferService;

    @GetMapping(value = "/exchangerates", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeRates getExchageList(){
        return transferService.exchangeRate();
    }


    @GetMapping
    public List<Transfer> getTransferDetails(){
        return transferService.findAll();
    }

    @PostMapping(value = "/iban")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMoney(@RequestBody TransferDto transferDto, @RequestParam(value = "sender") String iban1, @RequestParam(value = "receiver") String iban2){
            transferService.sendMoney(transferDto, iban1, iban2);
    }

}
