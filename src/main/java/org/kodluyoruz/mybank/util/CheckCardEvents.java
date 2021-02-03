package org.kodluyoruz.mybank.util;

import java.util.Random;

public class CheckCardEvents {

    private static final int cardNoCount = 16;
    private static final int cardFieldCount = 4;
    private static final int ccvNo = 3;

    public String createCardNo(){
        String cardNo;
        Random value = new Random();
        cardNo = "";
        int count = 0;
        int n = 0;
        for (int i = 0; i < cardNoCount; i++) {
            if (count == cardFieldCount) {
                cardNo += " ";
                count = 0;
            } else
                n = value.nextInt(10);
            cardNo += Integer.toString(n);
            count++;
        }
        return cardNo;
    }

    public int ccvNo(){
        String ccv;
        Random value = new Random();
        ccv = "";
        int count = 0;
        int n = 0;
        for (int i = 0; i < ccvNo; i++) {
            if (count == ccvNo) {
                ccv += " ";
                count = 0;
            } else
                n = value.nextInt(10);
            ccv += Integer.toString(n);
            count++;
        }
        return Integer.parseInt(ccv);
    }

}
