Feature: NewTours Login Functionality

  @Login
  Scenario: Verify user can log in with valid credentials
    Given I am on the NewTours Login page
    When I log in with the registered credentials
    And I click the submit button
    Then I should be navigated to the "Login Successfully" page
