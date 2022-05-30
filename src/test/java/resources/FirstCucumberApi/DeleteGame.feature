
Feature: Delete a video game from the catalogue

  Scenario: Delete video game by ID
    Given Catalogue with all video games
    When Delete video game by id 11
    Then return status 200