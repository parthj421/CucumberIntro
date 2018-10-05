@manualTests 
Feature: Penalty


  Scenario: BackOffice Penalty request is approved by Bank (uid:a0c0f7cb-647e-4b55-a750-a2876df00af2)
    Given an Inspection tap is sent to BackOffice
    And BackOffice determines to charge a Penalty to a card
    When BackOffice sends a Penalty request of amount $ 13.50 for a visa card
    Then Whisper creates a Penalty Auth request
    And Bank approves Penalty Auth request
    And Settlement request of amount $ 13.50 is sent

  Scenario: BackOffice Penalty request is denied by Bank (uid:0d042bd5-386c-43b3-b42a-7555f69a5f15)
    Given an Inspection tap is sent to BackOffice
    And BackOffice determines to charge a Penalty to a card
    When BackOffice sends a Penalty request of amount $ 13.05 for a visa card
    Then Whisper creates a Penalty Auth request of $ 13.05
    And Bank declines Penalty Auth request
    And $ 13.05 is added to the Debt
