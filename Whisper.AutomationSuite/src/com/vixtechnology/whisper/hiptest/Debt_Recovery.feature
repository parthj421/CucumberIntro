@manualTests 
Feature: Debt Recovery


  Scenario: Debt Recovery Authorisation approved (uid:1be07dfc-f36b-43ab-a508-0ec86be0be67)
    Given A card has pending debt of $18.60 and is on Deny List
    When Debt Recovery script runs
    Then Whisper creates a Debt Auth request of $18.60
    And Bank approves Debt Auth request
    And Settlement request of amount $ 18.60 is sent
    And card is removed from the DenyList

  Scenario: Debt Recovery Authorisation declined (uid:3108f4b4-3006-469b-8898-705c96ae9680)
    Given A card has pending debt of $18.60 and is on Deny List
    When Debt Recovery script runs
    Then Whisper creates a Debt Auth request of $18.55
    And Bank declines Debt Auth request
    And card is added to DenyList
    And card debt amount remains the same
