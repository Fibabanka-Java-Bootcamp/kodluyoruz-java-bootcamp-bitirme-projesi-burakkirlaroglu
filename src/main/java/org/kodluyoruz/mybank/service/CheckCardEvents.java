package org.kodluyoruz.mybank.service;

import java.util.Random;

public abstract class CheckCardEvents {


    public String createCardNo(){
        String cardNo;
        Random value = new Random();
        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        cardNo = "";

        int count = 0;
        int n = 0;
        for (int i = 0; i < 16; i++) {
            if (count == 4) {
                cardNo += " ";
                count = 0;
            } else
                n = value.nextInt(10);
            cardNo += Integer.toString(n);
            count++;
        }
        return cardNo;
    }

}
