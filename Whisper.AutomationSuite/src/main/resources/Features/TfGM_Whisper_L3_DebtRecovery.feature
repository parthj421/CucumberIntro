Feature: Debt Recovery Auth is Validated 
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
Scenario: Debt Recovery verification of BarclayCard1
    Given Card details of Visa BarclayCard 1 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And DebtRecovery PEM logs should match with the expected data
 
   	@Priority-high     
Scenario: Debt Recovery verification of BarclayCard3
    Given Card details of Visa BarclayCard 3 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And DebtRecovery PEM logs should match with the expected data
     
    	@Priority-high     
Scenario: Debt Recovery verification of BarclayCard4
    Given Card details of Visa BarclayCard 4 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And DebtRecovery PEM logs should match with the expected data
 
  	@Priority-high     
Scenario: Debt Recovery verification of BarclayCard5
    Given Card details of Visa BarclayCard 5 is available
     When Card is tapped on the FrontOffice validator
     Then A tap should happen
     And DebtRecovery PEM logs should match with the expected data
     
	