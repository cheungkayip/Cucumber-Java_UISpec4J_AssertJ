# Created by kayipcheung at 21/12/2016
@Smoke
Feature: Start application and click all buttons
  As an user
  I want to start the application
  So that i can click all the buttons

  Scenario: Click Button 1
    Given I have opened the Java Swing GUI and check "button1"
    When I click the button
    Then I should see the JTextField change to "You have clicked Button1"

  Scenario: Click Button 2
    Given I have opened the Java Swing GUI and check "button2"
    When I click the button
    Then I should see the JTextField change to "You have clicked Button2"

  Scenario: Click Button 3
    Given I have opened the Java Swing GUI and check "button3"
    When I click the button
    Then I should see the JTextField change to "You have clicked Button3"

  Scenario: Click Button 4
    Given I have opened the Java Swing GUI and check "Long-Named Button 4"
    When I click the button
    Then I should see the JTextField change to "You have clicked Long-Named Button 4"

  Scenario: Click Button 5
    Given I have opened the Java Swing GUI and check "button5"
    When I click the button
    Then I should see the JTextField change to "You have clicked Button5"