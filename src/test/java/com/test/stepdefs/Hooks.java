package com.test.stepdefs;


import com.test.utils.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class Hooks extends CommonFunctions {
    @Before
    public void SetUp() {
        if(driver == null){
           driver = new Driver().instance();

           if((System.getProperty("zapEnabled", "true").equals("true"))){
               try {
                   Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + "/zapReport.xml"));
                   Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + "/zapReport.html"));
               }
               catch(NoSuchFileException e){
                   System.out.println("No such file/directory exists");
               }
               catch(DirectoryNotEmptyException e)
               {
                   System.out.println("Directory is not empty.");
               }
               catch(IOException e)
               {
                   System.out.println("Invalid permissions.");
               }
               System.out.println("File Deletion successful.");
           }
        }
        driver.manage().deleteAllCookies();
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()) {
            byte[] scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(scrFile, "image/");
        }
        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;
    }
}
