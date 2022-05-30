
Feature: Update existing video game

  Scenario: Update video game name by Id
    Given Video games catalogue
    When update game by id 13
    Then status is 200
