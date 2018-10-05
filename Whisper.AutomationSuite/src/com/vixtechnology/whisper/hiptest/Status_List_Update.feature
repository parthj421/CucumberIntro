@manualTests 
Feature: Status List Update


  Scenario: BackOffice adds card to DenyList (uid:530a3fd0-d2fa-47ef-870c-2c2f3f5cdb90)
    Given A Visa card that is not in DenyList is available
    When BackOffice sends a request to add the card on DenyList
    Then card is added to DenyList
    And a Status List Update message is sent to Backoffice

  Scenario: BackOffice removes card from DenyList (uid:20e64762-6872-43b7-b5e2-571e80f3032f)
    Given A Visa card that is not in DenyList is available
    When BackOffice sends a request to remove the card from DenyList
    Then card is removed from the DenyList
    And a Status List Update message is sent to Backoffice

  Scenario Outline: BackOffice adds card to PilotList (<hiptest-uid>)
    Given A Visa card that is not in PilotList is available
    When BackOffice sends a request to add the card on PilotList
    Then card is added to PilotList
    And a Status List Update message is sent to Backoffice

    Examples:
      | ListName | hiptest-uid |
      | DenyList | uid:1a80e9b2-c555-4030-b0b5-657510a79da2 |
      | PilotList | uid:7d36a15c-f94d-4390-a022-653715bb11fa |

  Scenario: BackOffice removes card from PilotList (uid:91b80109-6e54-473a-a301-38bc00e6db39)
    Given A Visa card that is not in PilotList is available
    When BackOffice sends a request to remove the card from PilotList
    Then card is removed from the PilotList
    And a Status List Update message is sent to Backoffice
