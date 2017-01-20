# Created by kayipcheung at 21/12/2016
@FormattedTextField
Feature: JFormattedTextField
  As an user
  I want to start the application
  So that i can fill in numbers only in the formatted text field

  Scenario: Fill numbers only in the formatted text field
    Given I should start the java swing gui
    When I should check for formatted textfield to be visible "123-45-6789"
    Then I should fill in a different value "abc-de-fghi" which is not allowed
    And I should fill in a good value "333-33-4444" and check if this is ok

