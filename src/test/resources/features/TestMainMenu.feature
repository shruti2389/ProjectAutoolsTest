Feature: Test main menu
  This is to test look & feel of the main menu

  Scenario: To test for background colour of the main menu
    Given User is on the home page
    Then User should see menu bar with background color "rgb(51, 51, 51)"
#
#  Scenario: To test the third tab on the main menu is Employee Details
#    Given User is on the home page
#    Then Validate that the third tab is "Employee Details"
#
#  Scenario: To test that the forth tab on the main menu is Regions Details
#    Given User is on the home page
#    Then Validate that the forth tab is "Region Details"