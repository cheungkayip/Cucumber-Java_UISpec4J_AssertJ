# Created by kayipcheung at 21/12/2016
@Smoke
Feature: JComboBox
  As an user
  I want to start the application
  So that i can click some JComboBox items and see some changes

  Scenario: Click from JComboBox with Hgap + Vgap 10
    Given I should start the java swing gui
    When I should check JComboBox "horGap" and "verGap" to be visible on the page
    Then I click "10" for the "horGap" combobox
    And I click "10" for the "verGap" combobox
    And I click the "Apply gaps" button
    And I should see the JTextField change to "You have set the Hgap: 10 and the Vgap to 10"

