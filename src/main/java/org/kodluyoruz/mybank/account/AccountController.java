package org.kodluyoruz.mybank.account;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Validated
@RestController
@RequestMapping("/v1/api/customer/account")
public class AccountController {
}
