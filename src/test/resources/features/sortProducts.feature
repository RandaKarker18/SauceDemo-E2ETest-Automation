@SortProducts
Feature: Sort products by price
    As a user of SauceDemo I want to sort products by price from low to high 
    So that I can see the cheapest items first
    
    Background:
    Given I navigate to the Sauce labs website
    When I fill the userName field
    And I fill the password field
    And I click on the Login button
    
    Scenario Outline: Sort products by <sortOption>
    When the user sorts products by "<sortOption>"
    Then the products should be displayed in order "<order>"

    Examples:
      | sortOption          | order      |
      | Price (low to high)  | ascending  |
      | Price (high to low)  | descending |
      | Name (A to Z)        | ascending  |
      | Name (Z to A)        | descending |