package com.test.pages;

public class BankAccountDetailsPage implements PageObject {

    public String URL(){

        return "http://localhost:9230/check-income-tax/income/bank-building-society-savings/accounts";
    }
    public String HEADING(){

        return "Interest earned from your accounts: details";
    }
    public String TITLE(){

        return "Interest earned from your accounts: details";
    }
}
