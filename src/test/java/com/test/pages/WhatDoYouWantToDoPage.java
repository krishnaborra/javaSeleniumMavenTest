package com.test.pages;

public class WhatDoYouWantToDoPage implements PageObject {
   public String URL(){

       return "http://localhost:9230/check-income-tax/what-do-you-want-to-do";
   }
    public String HEADING(){

       return "Choose tax year";
    }
    public String TITLE(){

        return "Choose tax year";
    }

    public static String CURRENT_YEAR_RADIO = "taxYears-currenttaxyear";
    public static String PREVIOUS_YEARS_RADIO = "taxYears-lasttaxyear";
    public static String NEXT_YEARS_RADIO = "taxYears-nexttaxyear";
}
