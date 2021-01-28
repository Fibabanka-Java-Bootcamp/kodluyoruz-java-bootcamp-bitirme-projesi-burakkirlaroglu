package org.kodluyoruz.mybank.card;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Controller
@RestController
@RequestMapping("/v1/api/customer/card")
public class CardController {
}
