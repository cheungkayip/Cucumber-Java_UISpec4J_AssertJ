# Created by kayipcheung at 21/12/2016
@JTree
Feature: JTree
  As an user
  I want to start the application
  So that i can click all the JTree items

  Scenario: Click from JTree1 the root
    Given I should start the java swing gui
    When I should check if the Jtree "JTree1" is available
    Then I click the Row "V1 One" in the JTree
    And I should see the JTextField change to "You have selected V1 One"

