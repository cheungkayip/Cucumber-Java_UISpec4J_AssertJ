# Created by kayipcheung at 21/12/2016

Feature: JList
  As an user
  I want to start the application
  So that i can click all the listitems

  Scenario: Click from JList 1 Item 0
    Given I should start the java swing gui
    When I should check JList1 if it is visible "List1"
    Then I click the listitem 0
    And I should see the JTextField change to "You have selected Item 0"

  Scenario: Click from JList 1 Item 1
    Given I should start the java swing gui
    When I should check JList1 if it is visible "List1"
    Then I click the listitem 1
    And I should see the JTextField change to "You have selected Item 1"

  Scenario: Click from JList 1 Item 2
    Given I should start the java swing gui
    When I should check JList1 if it is visible "List1"
    Then I click the listitem 2
    And I should see the JTextField change to "You have selected Item 2"

  Scenario: Click from JList 2 Item 3
    Given I should start the java swing gui
    When I should check JList1 if it is visible "List2"
    Then I click the listitem 3
    And I should see the JTextField change to "You have selected Item 3"

  Scenario: Click from JList 2 Item 4
    Given I should start the java swing gui
    When I should check JList1 if it is visible "List2"
    Then I click the listitem 4
    And I should see the JTextField change to "You have selected Item 4"

  Scenario: Click from JList 2 Item 5
    Given I should start the java swing gui
    When I should check JList1 if it is visible "List2"
    Then I click the listitem 5
    And I should see the JTextField change to "You have selected Item 5"

  Scenario: Click from JList 3 Item 6
    Given I should start the java swing gui
    When I should check JList1 if it is visible "List3"
    Then I click the listitem 6
    And I should see the JTextField change to "You have selected Item 6"

  Scenario: Click from JList 3 Item 7
    Given I should start the java swing gui
    When I should check JList1 if it is visible "List3"
    Then I click the listitem 7
    And I should see the JTextField change to "You have selected Item 7"

  Scenario: Click from JList 3 Item 8
    Given I should start the java swing gui
    When I should check JList1 if it is visible "List3"
    Then I click the listitem 8
    And I should see the JTextField change to "You have selected Item 8"