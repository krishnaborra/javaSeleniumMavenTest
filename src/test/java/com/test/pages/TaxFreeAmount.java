package com.test.pages;

import com.test.utils.DateConfig;

public class TaxFreeAmount extends DateConfig implements PageObject {

    public String URL(){

        return "http://localhost:9230/check-income-tax/tax-free-allowance";
    }
    public String TITLE(){

        return "Your tax-free amount for 6 April " + currentYear + " to 5 April " + currentYearPlusOne;
    }
    public String HEADING(){

        return "Your tax-free amount for 6 April " + currentYear + " to 5 April " + currentYearPlusOne;
    }
}
