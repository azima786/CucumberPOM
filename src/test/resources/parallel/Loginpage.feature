@All
Feature: Login Page Feature

  @Smoke @Regression
  Scenario: Login Page Title
    Given user is on login page
    When user gets the title of the page
    Then page title should be "Account Login"

  @Regression
  Scenario: Forgot Password Link
    Given user is on login page
    Then forgot your password link should be displayed

  @Prod @qa-ready @Epic-900
  Scenario: Login with correct credentials
    Given user is on login page
    When user enters username "azimakeshwani@gmail.com"
    And user enters password "Selenium@12345"
    And user clicks on login button
    Then user gets the title of the page
    And page title should be "My Account"
