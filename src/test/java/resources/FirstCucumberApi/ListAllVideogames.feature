
Feature: List all video games
  Background:
    * URL

  Scenario: List all video games from the catalogue
    Given Catalogue with video games
    When Request all video games
    Then Successful status 200

    Scenario: Get video game by Id
      Given  Video game with id 4
      When Read the game
      Then Successful status 200