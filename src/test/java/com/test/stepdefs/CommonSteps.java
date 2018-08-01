package com.test.stepdefs;

import com.test.pages.IncomeTaxEstimatePage;
import com.test.pages.LoginPage;
import com.test.pages.WhatDoYouWantToDoPage;
import com.test.utils.Waits;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class CommonSteps extends CommonFunctions {
    Waits wait = new Waits();

    @Given("^I have been authenticated as user with nino (.+)$")
    public void i_have_been_authenticated_as_user_with_nino(String nino) {
        Login(nino);
    }

    @Given("^I am on what do you want to do page and select (.+) year$")
    public void i_am_on_what_do_you_want_to_do_page_and_select_year(String chooseYear) {
        switch(chooseYear) {
            case "current":
                wait.WaitForElementToPresentID(driver, WhatDoYouWantToDoPage.CURRENT_YEAR_RADIO);
                driver.findElement(By.id(WhatDoYouWantToDoPage.CURRENT_YEAR_RADIO)).click();
                break;
            case "previous":
                wait.WaitForElementToPresentID(driver, WhatDoYouWantToDoPage.PREVIOUS_YEARS_RADIO);
                driver.findElement(By.id(WhatDoYouWantToDoPage.PREVIOUS_YEARS_RADIO)).click();
                break;
            case "next":
                wait.WaitForElementToPresentID(driver, WhatDoYouWantToDoPage.NEXT_YEARS_RADIO);
                driver.findElement(By.id(WhatDoYouWantToDoPage.NEXT_YEARS_RADIO)).click();
                break;
        }
        driver.findElement(By.className("button")).click();
    }

    @Then("^I should be presented with \"([^\"]*)\" page$")
    public void i_should_be_presented_with_page(String pageName) {

        AssertNavigationToPage(PageReturn(pageName));
    }

    @Then("^I navigate to the \"([^\"]*)\" page$")
    public void i_navigate_to_the_page(String pageName) {
        navigateToPage(pageName);
    }

    @Then("^I click on (.+) button$")
    public void i_click_on_view_detailed_income_tax_estimate_button(String btnName) {
        driver.findElement(By.id(IncomeTaxEstimatePage.VIEWDETAILESTBTN_ID)).click();
    }

}
