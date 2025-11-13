Feature: Account Management

  @AccountManagement
  Scenario: Register a new user
    Given I navigate to the NewTours Registration page
    When I enter personal details and contact information
    And I enter user credentials with unique data
    And I submit the registration form
    Then I should see the successful registration message
