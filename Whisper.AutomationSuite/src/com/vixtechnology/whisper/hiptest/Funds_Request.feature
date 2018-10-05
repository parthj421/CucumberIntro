@manualTests 
Feature: Funds Request


  Scenario Outline: BackOffice sends Funds Request (<hiptest-uid>)
    Given Two taps marking end of a journey are received by BackOffice
    And BackOffice calculates fares
    When BackOffice sends the fundsreqest of Amount against tapped card "10.50"
    Then Whisper receives the  funds request against the card
    And BO receives Funds Result message

    Examples:
      | Amount | hiptest-uid |
      | 10 | uid:0402593e-288f-4233-ac1f-d33448daf0db |
      | 20 | uid:f65ee151-a001-4c34-99c5-f0f2cd27af86 |
