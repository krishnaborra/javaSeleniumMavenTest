package com.test.pages;

public class IncomeTaxSummaryPage implements PageObject {

    public String URL(){

        return "http://localhost:9230/check-income-tax/income-summary";
    }
    public String TITLE(){

        return "Your PAYE Income Tax summary for 6 April 2018 to 5 April 2019";
    }
    public String HEADING(){

        return "Your PAYE Income Tax summary for 6 April 2018 to 5 April 2019";
    }

    public static String UNTAXEDINTEREST_ID = "otherIncomeSources1DetailsLink";
    public static String INCOMETAXESTIMATELINK_ID = "estimatedIncomeTaxLink";
}
