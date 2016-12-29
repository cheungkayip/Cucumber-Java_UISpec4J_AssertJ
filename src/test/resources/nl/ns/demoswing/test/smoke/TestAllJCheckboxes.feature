# Created by kayipcheung at 21/12/2016
@Smoke
Feature: JCheckBox
  As an user
  I want to start the application
  So that i can click all the checkboxes

  Scenario: Click Checkbox 1
    Given I should start the java swing gui
    When I should check for JCheckbox if they are there "checkbox1"
    Then I click the checkbox
    And I should see the JTextField change to "You have selected Checkbox1"

  Scenario: Click Checkbox 2
    Given I should start the java swing gui
    When I should check for JCheckbox if they are there "checkbox2"
    Then I click the checkbox
    And I should see the JTextField change to "You have selected Checkbox2"

  Scenario: Click Checkbox 3
    Given I should start the java swing gui
    When I should check for JCheckbox if they are there "checkbox3"
    Then I click the checkbox
    And I should see the JTextField change to "You have selected Checkbox3"