@zap
Feature: Run zap scan after execution of all tests

  Scenario: After all the tests have finished executing run zap spider and zap active scan
    When Spider scan is triggered and allowed to complete
    When Active scan is triggered and allowed to complete
    When Zap scan results are exported to an external xml and html file
    Then Zap scan results are generated successfully