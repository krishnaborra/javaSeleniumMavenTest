package com.test.utils;

import org.joda.time.DateTime;

public class DateConfig {

    DateTime today = DateTime.now();
    String currentDate = today.toString("d MMMM yyyy");

    private String GetPreviousYear(Integer year){
        boolean ispastAprilSix = today.getMonthOfYear() > 4 || (today.getMonthOfYear() == 4 && today.getDayOfMonth() >= 6);

        if(ispastAprilSix) {
            return today.withYear(today.getYear() - year).toString("yyyy");
        }
        else {
            return today.withYear(today.getYear() - (year + 1)).toString("yyyy");
        }
    }
    
    private String GetFutureYear(Integer year) {
        return GetPreviousYear(year*(-1));
    }

    public String  currentYear = GetPreviousYear(0);
    public String  currentYearMinusOne = GetPreviousYear(1);
    public String  currentYearMinusTwo = GetPreviousYear(2);
    public String  currentYearMinusThree = GetPreviousYear(3);
    public String  currentYearMinusFour = GetPreviousYear(4);
    public String  currentYearPlusOne = GetFutureYear(1);

    public String lastYearDate = DateTime.now().withYear(DateTime.now().getYear() - 1).toString("d MMMM yyyy");
    
    
}
