@manualTests 
Feature: End of Period Auth


  Scenario: Generate End of Period Authorisation (uid:e4859eb0-5f58-4cf1-bb51-0f70a0efc450)
    Given BackOffice has send a FundRequest of 10.50 for a visa card
    When End of Period Script is run
    Then an End of Period Authorisation request with Authorisation amount 10.50 is sent for the card
