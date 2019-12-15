package com.github.kfang;

import com.opencsv.bean.CsvCustomBindByName;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class SalesReceipt {

    @CsvCustomBindByName(column = "Date", converter = TextToStringProperty.class)
    private SimpleStringProperty date;
    @CsvCustomBindByName(column = "Name", converter = TextToStringProperty.class)
    private SimpleStringProperty name;
    @CsvCustomBindByName(column = "Name Address", converter = TextToStringProperty.class)
    private SimpleStringProperty address;
    @CsvCustomBindByName(column = "Account", converter = TextToStringProperty.class)
    private SimpleStringProperty account;
    @CsvCustomBindByName(column = "Class", converter = TextToStringProperty.class)
    private SimpleStringProperty fund;
    @CsvCustomBindByName(column = "Amount", converter = TextToDoubleProperty.class)
    private SimpleDoubleProperty amount;
    @CsvCustomBindByName(column = "Check #", converter = TextToStringProperty.class)
    private SimpleStringProperty check;

    public SalesReceipt() {
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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
        return this.address.get();
    }

    public SimpleStringProperty addressProperty() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getAccount() {
        return account.get();
    }

    public SimpleStringProperty accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    public String getFund() {
        return fund.get();
    }

    public SimpleStringProperty fundProperty() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund.set(fund);
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public String getCheck() {
        return check.get();
    }

    public SimpleStringProperty checkProperty() {
        return check;
    }

    public void setCheck(String check) {
        this.check.set(check);
    }
}
