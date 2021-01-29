package org.kodluyoruz.mybank.card;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Controller
@RestController
@RequestMapping("/v1/api/customer/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardDto create(@RequestBody CardDto cardDto){
        Card card = new Card();
        return cardService.create(cardDto.toCard()).toCardDto();
    }

    @GetMapping(params = {"page","size"})
    public List<CardDto> list(@RequestParam("page") int page, @RequestParam("size") int size){
        return cardService.list(PageRequest.of(page,size)).stream()
                .map(Card::toCardDto)
                .collect(Collectors.toList());
    }
}
