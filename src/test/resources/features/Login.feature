Feature: Login feature

  Scenario: Login success
    Given I open Login page
    When I enter email 'demo@class.com'
    And I enter password 'te$t$tudent'
    And I click submit
    Then I am logged in
