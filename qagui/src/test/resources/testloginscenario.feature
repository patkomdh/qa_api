Feature: User panel

  @wordpress @login @userprofile
  Scenario: User login
    Given User starts on main page
    When User logs to the user panel
    Then User can modify user profile