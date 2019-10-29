package com.github.kfang;

import java.time.LocalDate;
import java.util.ArrayList;

public class SalesReceiptGenerator {

    public static ArrayList<SalesReceipt> generate(int count) {
        ArrayList<SalesReceipt> list = new ArrayList<SalesReceipt>();
        for (int c = 0; c < count; c++) {
            SalesReceipt receipt = new SalesReceipt();
            receipt.setDate(LocalDate.now().toString());
            receipt.setName("John Doe");
            receipt.setAddress("124908 Amberwood Lane");
            receipt.setAccount("AAA Ministry");
            receipt.setFund("XXX Fund");
            receipt.setAmount(Math.round(Math.random() * 10000));
            receipt.setCheck("YYYYY");
            list.add(receipt);
        }
        return list;
    }

}
