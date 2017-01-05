# Created by kayipcheung at 21/12/2016
@AbstractButton
Feature: AbstractButton
  As an user
  I want to start the application
  So that i can click the AbstractButton and see it change two times

  Scenario: Click AbstractButton 1
    Given I should start the java swing gui
    When I should check for buttons and textfield visibility "AbstractButton"
    Then I should check if the AbstractButton label has the text "(You Have to click me!)"
    Then I click the AbstractButton
    And I should see the AbstractButton label changed to "(Good Job! You have clicked me the first time!)"
    Then I click the AbstractButton
    And I should see the AbstractButton label changed to "(What!? You have clicked me again??)"
