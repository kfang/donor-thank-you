package com.github.kfang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SalesReceiptGroup {

    public static List<SalesReceiptGroup> fromReceipts(List<SalesReceipt> receipts) {
        HashMap<String, SalesReceiptGroup> results = new HashMap<String, SalesReceiptGroup>();

        receipts.forEach((receipt) -> {
            String key = receipt.getName() + "/" + receipt.getAddress();
            if (results.containsKey(key)) {
                results.get(key).addReceipt(receipt);
            } else {
                SalesReceiptGroup newGroup = new SalesReceiptGroup();
                newGroup.addReceipt(receipt);
                results.put(key, newGroup);
            }
        });

        return new ArrayList<>(results.values());
    }

    private ArrayList<SalesReceipt> receipts;

    public SalesReceiptGroup() {
        this.receipts = new ArrayList<>();
    }

    public ArrayList<SalesReceipt> getReceipts() {
        return receipts;
    }

    public void addReceipt(SalesReceipt receipt) {
        this.receipts.add(receipt);
    }

}
