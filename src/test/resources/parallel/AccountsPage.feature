Feature: Account Page Feature

  Background:
    Given User has already logged into application
      | username                | password       |
      | azimakeshwani@gmail.com | Selenium@12345 |

  Scenario: Accounts Page title
    Given User is on Accounts Page
    When user gets the title of the page
    Then page title should be "My Account"

  Scenario: Account section count
    Then user gets Accounts section
      | My Account           |
      | My Orders            |
      | My Affiliate Account |
      | Newsletter           |
    And accounts section count should be 4
