package org.kodluyoruz.mybank.service.impl;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.dto.TransferDto;
import org.kodluyoruz.mybank.entity.Account;
import org.kodluyoruz.mybank.util.ExchangeRates;
import org.kodluyoruz.mybank.entity.Transfer;
import org.kodluyoruz.mybank.repository.AccountRepository;
import org.kodluyoruz.mybank.repository.TransferRepository;
import org.kodluyoruz.mybank.service.TransferService;
import org.kodluyoruz.mybank.transformer.TransferTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransferServiceImp implements TransferService {

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final TransferTransformer transferTransformer;

    @Autowired
    private final TransferRepository transferRepository;

    @Autowired
    private final AccountRepository accountRepository;



    @Override
    public ExchangeRates exchangeRate() {
        return restTemplate.getForObject("https://api.exchangeratesapi.io/latest?base=TRY", ExchangeRates.class);
    }


    @Override
    public double usdRate() {
        double result = exchangeRate().getRates().get("TRY") / exchangeRate().getRates().get("USD");
        return result;
    }

    @Override
    public double eurRate() {
        double result = exchangeRate().getRates().get("TRY") / exchangeRate().getRates().get("EUR");
        return result;
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }


    @Override
    @Transactional(rollbackFor = NullPointerException.class)
    public void sendMoney(TransferDto transferDto, String senderIban, String receiverIban) {

        try {
            Transfer transfer = transferTransformer.accountTransfer(transferDto);
            double amount = transfer.getAmount();
            String currency = transfer.getCurrency();
            String accountType = transfer.getAccountType();
            senderIban = transfer.getSenderIban();
            receiverIban = transfer.getReceiverIban();
            accountsTransfer(transfer, amount, senderIban, receiverIban);
            Transfer transfer1 = new Transfer();
            saveTransfer(transfer1, amount, currency, accountType,senderIban,receiverIban);
        }catch (NullPointerException e){
            throw new NullPointerException("Null value!"+e);
        }

    }

    public void saveTransfer(Transfer transfer, double amount, String currency, String accountType, String senderIban, String receiverIban){
        transfer.setAmount(amount);
        transfer.setCurrency(currency);
        transfer.setAccountType(accountType);
        transfer.setSenderIban(senderIban);
        transfer.setReceiverIban(receiverIban);
        transferRepository.save(transfer);
    }

    public void accountsTransfer(Transfer transfer, double amount, String senderIban, String receiverIban){

        String euro = "EUR";
        String tl = "TRY";
        String usd = "USD";

        Account accountSend = transfer.getAccounts().get(0);
        Account accountTake = transfer.getAccounts().get(1);

        if (senderIban.equals(accountSend.getIban()) & receiverIban.equals(accountTake.getIban())){

                if (accountSend.getAccountType().equals(accountTake.getAccountType()) && accountSend.getCurrency().equals(accountTake.getCurrency())) {
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + amount);
                }
                if (accountSend.getCurrency().equals(euro) & accountTake.getCurrency().equals(tl)){
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + (amount * eurRate()));
                }
                else if (accountSend.getCurrency().equals(tl) & accountTake.getCurrency().equals(euro)){
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + (amount / eurRate()));
                }else if (accountSend.getCurrency().equals(usd) & accountTake.getCurrency().equals(tl)){
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + (amount * usdRate()));
                }else if (accountSend.getCurrency().equals(tl) & accountTake.getCurrency().equals(usd)){
                    accountSend.setBalance(accountSend.getBalance() - amount);
                    accountTake.setBalance(accountTake.getBalance() + (amount / usdRate()));
                }else if (accountSend.getCurrency().equals(euro) & accountTake.getCurrency().equals(usd)){
                    double euroAmount = amount * (usdRate() / eurRate());
                    double usdAmount = amount * (eurRate() / usdRate());
                    accountSend.setBalance(accountSend.getBalance() - euroAmount);
                    accountTake.setBalance(accountTake.getBalance() + usdAmount);
                }else if (accountSend.getCurrency().equals(usd) & accountTake.getCurrency().equals(euro)){
                    double euroAmount = amount * (usdRate() / eurRate());
                    double usdAmount = amount * (eurRate() / usdRate());
                    accountSend.setBalance(accountSend.getBalance() - usdAmount);
                    accountTake.setBalance(accountTake.getBalance() + euroAmount);
                }

            accountRepository.save(accountSend);
            accountRepository.save(accountTake);
        }

    }

}