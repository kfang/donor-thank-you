package com.github.kfang;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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
                SalesReceiptGroup newGroup = new SalesReceiptGroup(receipt.getName(), receipt.getAddress());
                newGroup.addReceipt(receipt);
                results.put(key, newGroup);
            }
        });

        return new ArrayList<>(results.values());
    }

    private ArrayList<SalesReceipt> receipts;
    private SimpleDoubleProperty totalAmount;
    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleIntegerProperty numReceipts;

    public SalesReceiptGroup(String name, String address) {
        this.receipts = new ArrayList<>();
        this.totalAmount = new SimpleDoubleProperty(0);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.numReceipts = new SimpleIntegerProperty(0);
    }

    public ArrayList<SalesReceipt> getReceipts() {
        return receipts;
    }

    public void addReceipt(SalesReceipt receipt) {
        this.receipts.add(receipt);

        // recalculate totalAmount
        Double sum = this.totalAmount.getValue() + receipt.getAmount();
        this.totalAmount.setValue(sum);

        // recalculate number of receipts
        this.numReceipts.setValue(this.receipts.size());
    }

    public double getTotalAmount() {
        return totalAmount.get();
    }

    public SimpleDoubleProperty totalAmountProperty() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount.set(totalAmount);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public int getNumReceipts() {
        return numReceipts.get();
    }

    public SimpleIntegerProperty numReceiptsProperty() {
        return numReceipts;
    }

    public void setNumReceipts(int numReceipts) {
        this.numReceipts.set(numReceipts);
    }
}
