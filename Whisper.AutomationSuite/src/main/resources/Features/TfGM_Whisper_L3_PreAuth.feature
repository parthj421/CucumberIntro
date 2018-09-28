Feature: PreAuth is validated
    # **In oder to** get money when the bank is closed
    # **As** an Account Holder
    # **I want to** withdraw cash from an ATM

 # @Priority-high
 # Scenario Outline: Account has sufficient funds (<hiptest-uid>)
#    Given the account balance is "$100"
 #   And the machine contains enough money
 #   And the card is valid
 #   When the Account Holder requests "<amount>"
 #   Then the ATM should dispense "<amount>"
 #   And the account balance should be "<ending_balance>"
 #   And the card should be returned

 #   Examples:
 #     | amount | ending_balance | hiptest-uid |
 #     | $100 | $0 |  |
 #     | $50 | $50 |  |
 #     | $20 | $80 |  |
      
	@Priority-high     
Scenario: PreAuth verification of BarclayCard1
    Given Card details of Visa BarclayCard 1 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And PreAuth PEM logs should match with the expected data
     
	@Priority-high     
Scenario: PreAuth verification of BarclayCard2
     Given Card details of Visa BarclayCard 2 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And PreAuth PEM logs should match with the expected data  
     
     @Priority-high     
Scenario: PreAuth verification of BarclayCard3
     Given Card details of Visa BarclayCard 3 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And PreAuth PEM logs should match with the expected data 
     
      @Priority-high     
Scenario: PreAuth verification of BarclayCard4
     Given Card details of Visa BarclayCard 4 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And PreAuth PEM logs should match with the expected data 
     
     
      @Priority-high     
Scenario: PreAuth verification of BarclayCard5
     Given Card details of Visa BarclayCard 5 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And PreAuth PEM logs should match with the expected data 
     
     
     @Priority-high     
Scenario: PreAuth verification of BarclayCard6
     Given Card details of Visa BarclayCard 6 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And PreAuth PEM logs should match with the expected data 
     
     
          @Priority-high     
Scenario: PreAuth verification of BarclayCard7
     Given Card details of Mastercard BarclayCard 7 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And PreAuth PEM logs should match with the expected data 
     
          
          @Priority-high     
Scenario: PreAuth verification of BarclayCard8
     Given Card details of Mastercard BarclayCard 8 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And PreAuth PEM logs should match with the expected data 
     

     
     