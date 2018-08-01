@wip
Feature: Opening a Bank or building society account
  As a PAYE tax payer
  I want to be able to tell HMRC that they incorrectly think I have a bank account
  So I ensure I'm paying the right amount of tax

  Scenario:1 I can successfully remove a bank account from my Tax account
    Given I have been authenticated as user with nino NINO
    Then I should be presented with "WDYWTD" page
    And I am on what do you want to do page and select current year
    Then I should be presented with "Income Tax Summary" page
    And I navigate to the "Bbsi overview" page
    Then I should be presented with "Bbsi overview" page
    And I navigate to the "Bank Account details" page
    Then I should be presented with "Bank Account details" page


  Scenario: 2- English user with Simple Tax view
    Given I have been authenticated as user with nino NINO
    And I am on what do you want to do page and select current year
    Then I should be presented with "Income Tax Summary" page
    When I navigate to the "Your Income Tax Estimate" page
    Then I should be presented with "Your Income Tax Estimate" page
    And I click on view detailed income tax estimate button
#    Then I should be presented with "Your Tax Free Amount" page