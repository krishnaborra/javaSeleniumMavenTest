package com.test.stepdefs;

import com.test.pages.*;
import com.test.utils.Driver;
import com.test.utils.Waits;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CommonFunctions {

    WhatDoYouWantToDoPage wDyWtD = new WhatDoYouWantToDoPage();

    public static WebDriver driver = new Driver().instance();
    Waits wait = new Waits();

    public void Login(String nino) {
        driver.get(LoginPage.URL_ID);
        wait.WaitForElementToPresentNAME(driver, LoginPage.CRED_ID_FIELD);

        driver.findElement(By.name(LoginPage.CRED_ID_FIELD)).sendKeys("test");
        driver.findElement(By.name(LoginPage.REDIRECT_URL_FIELD)).sendKeys(LoginPage.REDIRECT_URL_PREFIX + wDyWtD.URL());
        Select dropDown = new Select(driver.findElement(By.name(LoginPage.CONFIDENCE_LEVEL_FIELD)));
        dropDown.selectByVisibleText("200");
        driver.findElement(By.name(LoginPage.NINO_FIELD)).sendKeys(nino);
        driver.findElement(By.className("button")).click();
        wait.WaitForElementToPresentID(driver, WhatDoYouWantToDoPage.CURRENT_YEAR_RADIO);
    }

    public PageObject PageReturn(String page){

        switch(page){
            case "WDYWTD":
                return new WhatDoYouWantToDoPage();
            case "Income Tax Summary":
                return new IncomeTaxSummaryPage();
            case "Bbsi overview":
                 return new BbsiOverviewPage();
            case "Bank Account details":
                return new BankAccountDetailsPage();
            case "Your Income Tax Estimate":
                return new IncomeTaxEstimatePage();
            case "Your Tax Free Amount":
                return new TaxFreeAmount();
            default: throw new RuntimeException("Page Name Not defined");
        }
    }

    public void AssertNavigationToPage(PageObject pageType){
        Assert.assertEquals(pageType.URL(), driver.getCurrentUrl());
        Assert.assertEquals(pageType.TITLE() + " - Check your Income Tax - GOV.UK", driver.getTitle());
        Assert.assertEquals(pageType.HEADING(), driver.findElement(By.tagName("h1")).getText());
    }

    public void navigateToPage(String pageName) {
        switch (pageName){
            case "Your Income Tax Estimate": wait.WaitForElementToPresentID(driver, IncomeTaxSummaryPage.INCOMETAXESTIMATELINK_ID);
                driver.findElement(By.id(IncomeTaxSummaryPage.INCOMETAXESTIMATELINK_ID)).click();
                break;
            case "Bbsi overview": wait.WaitForElementToPresentID(driver, IncomeTaxSummaryPage.UNTAXEDINTEREST_ID);
                driver.findElement(By.id(IncomeTaxSummaryPage.UNTAXEDINTEREST_ID)).click();
                break;
            case "Bank Account details": wait.WaitForElementToPresentID(driver, BbsiOverviewPage.CHECKYOURACCOUNT_BUTTON);
                driver.findElement(By.id(BbsiOverviewPage.CHECKYOURACCOUNT_BUTTON)).click();
                break;
        }
    }
}
