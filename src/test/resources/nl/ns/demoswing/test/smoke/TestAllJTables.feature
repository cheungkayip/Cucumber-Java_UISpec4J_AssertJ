# Created by kayipcheung at 21/12/2016
@Tables
Feature: JTable
  As an user
  I want to start the application
  So that i can click all the Jtable items

  Scenario: Click from JTable Row0-Column0
    Given I should start the java swing gui
    When I should check if JTable "Table Example" is available
    Then I click the Row "0" and Column "0"
    And I should see the JTextField change to "You have selected Row1-Column1"

  Scenario: Click from JTable Row1-Column0
    Given I should start the java swing gui
    When I should check if JTable "Table Example" is available
    Then I click the Row "1" and Column "0"
    And I should see the JTextField change to "You have selected Row2-Column1"

  Scenario: Click from JTable Row2-Column0
    Given I should start the java swing gui
    When I should check if JTable "Table Example" is available
    Then I click the Row "2" and Column "0"
    And I should see the JTextField change to "You have selected Row3-Column1"

  Scenario: Click from JTable Row0-Column1
    Given I should start the java swing gui
    When I should check if JTable "Table Example" is available
    Then I click the Row "0" and Column "1"
    And I should see the JTextField change to "You have selected Row1-Column2"

  Scenario: Click from JTable Row1-Column1
    Given I should start the java swing gui
    When I should check if JTable "Table Example" is available
    Then I click the Row "1" and Column "1"
    And I should see the JTextField change to "You have selected Row2-Column2"

  Scenario: Click from JTable Row2-Column1
    Given I should start the java swing gui
    When I should check if JTable "Table Example" is available
    Then I click the Row "2" and Column "1"
    And I should see the JTextField change to "You have selected Row3-Column2"

  Scenario: Click from JTable Row0-Column2
    Given I should start the java swing gui
    When I should check if JTable "Table Example" is available
    Then I click the Row "0" and Column "2"
    And I should see the JTextField change to "You have selected Row1-Column3"

  Scenario: Click from JTable Row1-Column2
    Given I should start the java swing gui
    When I should check if JTable "Table Example" is available
    Then I click the Row "1" and Column "2"
    And I should see the JTextField change to "You have selected Row2-Column3"

  Scenario: Click from JTable Row2-Column2
    Given I should start the java swing gui
    When I should check if JTable "Table Example" is available
    Then I click the Row "2" and Column "2"
    And I should see the JTextField change to "You have selected Row3-Column3"