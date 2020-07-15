Feature: Test login
  This is to test the login functionality

  Scenario: To test positive test case for sign in
    Given User is on the login page
    When User enters "shruti@kubecloudsinc.com" as username
    And User enters "Testing1" as password
    And clicks Sign in
    Then User is shown welcome page with msg "Welcome to Auto Tools"

  Scenario: To test negative test case for sign in
    Given User is on the login page
    When User enters "shruti@kubecloudsinc.com" as username
    And User enters "Test" as password
    And clicks Sign in
    Then User is shown error page with msg "Your login attempt was not successful, please try again."

  Scenario: To test negative test case for sign in
    Given User is on the login page
    When User enters "shruti@kubecloudsinc.com" as username
    And User enters " " as password
    And clicks Sign in
    Then User is shown error page with msg "Your login attempt was not successful, please try again."