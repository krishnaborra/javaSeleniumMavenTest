package com.test.pages;

public class BbsiOverviewPage implements PageObject {
    public String URL() {
        return "http://localhost:9230/check-income-tax/income/bank-building-society-savings";
    }
    public String HEADING() {
        return "Interest earned from your accounts: overview";
    }
    public String TITLE() {
        return "Interest earned from your accounts: overview";
    }

    public static String CHECKYOURACCOUNT_BUTTON = "checkYourAccounts";
}
