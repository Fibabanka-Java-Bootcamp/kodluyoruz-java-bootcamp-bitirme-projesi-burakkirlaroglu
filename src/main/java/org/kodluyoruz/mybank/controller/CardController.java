package org.kodluyoruz.mybank.controller;

import org.kodluyoruz.mybank.dto.CardDto;
import org.kodluyoruz.mybank.service.CardService;
import org.kodluyoruz.mybank.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Controller
@RestController
@RequestMapping("/v1/api/card")
public class CardController {

    @Autowired
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PutMapping(value = "/custno/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCart(@RequestBody CardDto cardDto, @PathVariable int id){
        cardService.addCart(id, cardDto);
    }

    @GetMapping(params = {"page","size"})
    public List<CardDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        return cardService.list(PageRequest.of(page,size)).stream()
                .map(Card::toCardDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/debtamount/{id}")
    public HashMap<String,Double> cardDebt(@PathVariable int id){
        return cardService.findCardDept(id);
    }
}
