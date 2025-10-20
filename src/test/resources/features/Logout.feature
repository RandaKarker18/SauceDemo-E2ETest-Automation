@Logout
Feature: Successful Logout
  As a user, I want to log out from the Sauce labs website

Background:
    Given I navigate to the Sauce labs website
    When I fill the userName field
    And I fill the password field
    And I click on the Login button

  @logout_success
  Scenario: I want to log out successfully
    When I click on the burger button in the top left
    And I click on the Logout button
    Then I am redirected to the login page "Swag Labs"
