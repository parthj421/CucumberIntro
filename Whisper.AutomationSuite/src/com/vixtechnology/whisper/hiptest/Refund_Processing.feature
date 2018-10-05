@manualTests 
Feature: Refund Processing


  Scenario: BackOffice Refund request (uid:0c3fcee3-6050-4c82-9f2c-e1a59a590728)
    Given BackOffice determines to send Refund to a card
    When BackOffice sends a Refund request of amount $ 14.50 for a visa card
    Then Whisper does not send the PreAuth request
    And Settlement request of amount $ 14.50 is sent
