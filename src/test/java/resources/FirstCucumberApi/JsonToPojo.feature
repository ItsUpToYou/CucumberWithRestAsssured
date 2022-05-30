
  Feature: Convert Json to Pojo

    Scenario: Compare Json object with the Pojo object
      Given Read the video games
      When Take video game with id 13
      Then Compare the name with Pojo


    Scenario: Extract json objects into Java objects
      Given Read the video games
      When Convert Json objects to Java
      Then Video game with name Mario exist