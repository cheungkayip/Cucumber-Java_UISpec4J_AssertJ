# Created by kayipcheung at 21/12/2016
@Buttons
Feature: JButton
  As an user
  I want to start the application
  So that i can click all the buttons

  Scenario: Click Button 1
    Given I should start the java swing gui
    When I should check for buttons and textfield visibility "button1"
    Then I click the button
    And I should see the JTextField change to "You have clicked Button1"

  Scenario: Click Button 2
    Given I should start the java swing gui
    When I should check for buttons and textfield visibility "button2"
    Then I click the button
    And I should see the JTextField change to "You have clicked Button2"

  Scenario: Click Button 3
    Given I should start the java swing gui
    When I should check for buttons and textfield visibility "button3"
    Then I click the button
    And I should see the JTextField change to "You have clicked Button3"

  Scenario: Click Button 4
    Given I should start the java swing gui
    When I should check for buttons and textfield visibility "Long-Named Button 4"
    Then I click the button
    And I should see the JTextField change to "You have clicked Long-Named Button 4"

  Scenario: Click Button 5
    Given I should start the java swing gui
    When I should check for buttons and textfield visibility "button5"
    Then I click the button
    And I should see the JTextField change to "You have clicked Button5"