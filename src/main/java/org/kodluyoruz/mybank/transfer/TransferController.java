package org.kodluyoruz.mybank.transfer;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/v1/api/customer/account/transfer")
public class TransferController {
}
