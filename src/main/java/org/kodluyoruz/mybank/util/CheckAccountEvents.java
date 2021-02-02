package org.kodluyoruz.mybank.util;

import org.kodluyoruz.mybank.entity.Account;
import java.util.Random;

public class CheckAccountEvents {

    enum Currency{
        TRY,
        USD,
        EUR
    }

    enum AccountType{
        CHECKING,
        SAVING
    }

    private static final int ibanNoCount = 12;
    private static final int ibanFieldCount = 4;

    public <T> String  createIban(String t) {
        String ibanNo = t;
        Random value = new Random();

        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        ibanNo += Integer.toString(r1) + Integer.toString(r2) + " ";

        int count = 0;
        int n = 0;
        for (int i = 0; i < ibanNoCount; i++) {
            if (count == ibanFieldCount) {
                ibanNo += " ";
                count = 0;
            } else
                n = value.nextInt(10);
            ibanNo += Integer.toString(n);
            count++;
        }
        return ibanNo;
    }

    //İsimleri tekrar kontrol et

    public String setAccountIban(Account account) {
        try {
            if (account.getIban() == null){
                if (account.getCurrency().equals(Currency.TRY.toString())) {
                    account.setIban(createIban("TR"));
                    return account.getIban();
                } else if (account.getCurrency().equals(Currency.USD.toString())) {
                    account.setIban(createIban("US"));
                    return account.getIban();
                } else if (account.getCurrency().equals(Currency.EUR.toString())) {
                    account.setIban(createIban("EU"));
                    return account.getIban();
                }
            }
        }catch (IllegalArgumentException e){
            System.out.println("3 para birimi var");
        }catch (NullPointerException e2){
            System.out.println("hata null");
        }
        return account.getIban();
    }

    public String setAccountType(Account account){
        try {
            if (account.getAccountType().equals(AccountType.CHECKING.toString())){
                account.setAccountType("CHECKING");
                return account.getAccountType();
            }else if (account.getAccountType().equals(AccountType.SAVING.toString())){
                account.setAccountType("SAVING");
                return account.getAccountType();
            }
        }catch (IllegalArgumentException e){
            System.out.println("Geçersiz");
        }
        return account.getAccountType();
    }


    }