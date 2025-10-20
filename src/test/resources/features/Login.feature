@login
Feature: Authentication
  As a user, I want to log in to the Sauce labs website

  @login_valid_credentials
  Scenario: I want to log in with valid credentials
    Given I navigate to the Sauce labs website
    When I fill the userName field
    And I fill the password field
    And I click on the Login button
    Then I am redirected to the Home page "Products"