package com.test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
    public void WaitForElementToPresentID(WebDriver driver, String id) {
        WebDriverWait driverWait  = new WebDriverWait(driver, 15);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    public void WaitForElementToPresentNAME(WebDriver driver, String name) {
        WebDriverWait driverWait  = new WebDriverWait(driver, 15);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
    }

}
