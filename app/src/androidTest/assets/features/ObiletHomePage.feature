# language: en
@obilet
Feature: Obilet Home Page Tests

  @smoke @regression
  Scenario: Verify all transportation options on home page
    Given the Obilet application is open
    And the home page is loaded
    When I click on the Bus option
    And I click on the Flight option
    And I click on the Hotel option
    And I click on the Car option
    And I click on the Ferry option
    Then verify all options are working properly
