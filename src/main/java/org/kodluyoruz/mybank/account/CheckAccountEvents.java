package org.kodluyoruz.mybank.account;

import java.util.Random;

public abstract class CheckAccountEvents {

    enum Currency {
        TRY,
        USD,
        EUR
    }

    enum AccountType{
        CHECKING,
        SAVING
    }

    public <T> String createIban(String t) {
        String ibanNo = t;
        Random value = new Random();

        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        ibanNo += Integer.toString(r1) + Integer.toString(r2) + " ";

        int count = 0;
        int n = 0;
        for (int i = 0; i < 12; i++) {
            if (count == 4) {
                ibanNo += " ";
                count = 0;
            } else
                n = value.nextInt(10);
            ibanNo += Integer.toString(n);
            count++;
        }
        return ibanNo;
    }

    public void checkedAccountIban(Account account) {
        if (account.getCurrency().equals(Currency.TRY.toString())) {
            account.setIban(createIban("TR"));
        } else if (account.getCurrency().equals(Currency.USD.toString())) {
            account.setIban(createIban("US"));
        } else if (account.getCurrency().equals(Currency.EUR.toString())) {
            account.setIban(createIban("EU"));
        }
    }

    public void chekedAccountType(Account account){
        if (account.getAccountType().equals(AccountType.CHECKING.toString())){
            account.setAccountType("CHECKING");
        }else if (account.getAccountType().equals(AccountType.SAVING.toString())){
            account.setAccountType("SAVING");
        }
    }
}