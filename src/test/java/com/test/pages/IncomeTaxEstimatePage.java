package com.test.pages;

public class IncomeTaxEstimatePage implements PageObject {

    public String URL(){

        return "http://localhost:9230/check-income-tax/paye-income-tax-estimate";
    }
    public String TITLE(){

        return "Your PAYE Income Tax estimate";
    }
    public String HEADING(){

        return "Your PAYE Income Tax estimate";
    }

    public static String VIEWDETAILESTBTN_ID = "detailEstimateView";
}
