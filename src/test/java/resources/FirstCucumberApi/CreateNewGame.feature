
Feature: Create new video game

  Scenario: Add new video game to existing catalogue
    Given Catalogue
    When Add new game with id 13
    Then status is 200