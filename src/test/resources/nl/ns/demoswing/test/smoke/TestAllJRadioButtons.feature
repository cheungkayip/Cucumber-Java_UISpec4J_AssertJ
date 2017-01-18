# Created by kayipcheung at 21/12/2016
@Radiobuttons
Feature: JRadioButton
  As an user
  I want to start the application
  So that i can click all the radiobuttons

  Scenario: Click RadioButton 1
    Given I should start the java swing gui
    When I should check for RadioButton if they are there "RadioButton1"
    Then I click the radiobutton "RadioButton1"
    And I should see the JTextField change to "You have selected RadioButton1"
    And I click the radiobutton "RadioButton1"
    And I should see the JTextField change to "You have Unselected RadioButton1"

  Scenario: Click RadioButton 2
    Given I should start the java swing gui
    When I should check for RadioButton if they are there "RadioButton2"
    Then I click the radiobutton "RadioButton2"
    And I should see the JTextField change to "You have selected RadioButton2"
    And I click the radiobutton "RadioButton2"
    And I should see the JTextField change to "You have Unselected RadioButton2"

  Scenario: Click RadioButton 3
    Given I should start the java swing gui
    When I should check for RadioButton if they are there "RadioButton3"
    Then I click the radiobutton "RadioButton3"
    And I should see the JTextField change to "You have selected RadioButton3"
    And I click the radiobutton "RadioButton3"
    And I should see the JTextField change to "You have Unselected RadioButton3"