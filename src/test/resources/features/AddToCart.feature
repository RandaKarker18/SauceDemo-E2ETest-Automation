@AddToCart
Feature: Add products to cart
  As a user, I want to add multiple products to my cart so that I can review them before checkout

Background:
    Given I navigate to the Sauce labs website
    When I fill the userName field
    And I fill the password field
    And I click on the Login button

 @AddToCart_Success
  Scenario: Successfully add multiple products to the cart
    When I add the following products to the cart:
	  | Sauce Labs Bike Light     |
	  | Sauce Labs Fleece Jacket  |
    Then the cart icon should show "2" items
    When I click on the cart icon
    Then I should be redirected to the Cart page titled "Your Cart"
    And I should see the following products in my cart:
      | Sauce Labs Bike Light    |
      | Sauce Labs Fleece Jacket |
