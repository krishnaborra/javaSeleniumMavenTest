package com.test.utils;

import org.openqa.selenium.WebDriver;

public class Driver {

    public static WebDriver instance() {
        return BrowserFactory.CreateBrowser();
    }

}
