package com.vixtechnology.whisper.hiptest;

import com.vixtechnology.whip.framework.WhipTest;
import com.vixtechnology.whip.framework.Log;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.Scenario;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class StepDefinitions extends WhipTest
{
    public Actionwords actionwords = Factory.createActionwords();

    @Before
    public void beforeCallingScenario(Scenario sc) 
    {
        Log.info("\n=======================================================================================================\n");
        Log.info("***** ----- " + sc.getName());
        Log.info("\n=======================================================================================================\n");

        actionwords.beforeCallingScenario();
    }

    @After
    public void afterCallingScenario() 
    {
        actionwords.afterCallingScenario();
    }


    @Given("^A Terminal is connected with the Network$")
    public void aTerminalIsConnectedWithTheNetwork() {
        actionwords.aTerminalIsConnectedWithTheNetwork();
    }

    @When("^A valid card is tapped on Terminal$")
    public void aValidCardIsTappedOnTerminal() {
        actionwords.aValidCardIsTappedOnTerminal();
    }

    @Then("^Terminal should display green lights and the card should be approved$")
    public void terminalShouldDisplayGreenLightsAndTheCardShouldBeApproved() {
        actionwords.terminalShouldDisplayGreenLightsAndTheCardShouldBeApproved();
    }

    @Given("^A visa card is available to generate the tap \"(.*)\"$")
    public void aVisaCardIsAvailableToGenerateTheTap(String cardSheme, DataTable datatable, String freeText) {
        actionwords.aVisaCardIsAvailableToGenerateTheTap(cardSheme, datatable, freeText);
    }

    @Given("^a Terminal is configured$")
    public void aTerminalIsConfigured() {
        actionwords.aTerminalIsConfigured();
    }

    @When("^Visa card is tapped on the Terminal$")
    public void visaCardIsTappedOnTheTerminal() {
        actionwords.visaCardIsTappedOnTheTerminal();
    }

    @Then("^a Tap is generated and progress of tap is monitored on WhisperMon$")
    public void aTapIsGeneratedAndProgressOfTapIsMonitoredOnWhisperMon() {
        actionwords.aTapIsGeneratedAndProgressOfTapIsMonitoredOnWhisperMon();
    }

    @Then("^Tap is received at the Back Office with same last 4 digits of the Visa card$")
    public void tapIsReceivedAtTheBackOfficeWithSameLast4DigitsOfTheVisaCard() {
        actionwords.tapIsReceivedAtTheBackOfficeWithSameLast4DigitsOfTheVisaCard();
    }

    @Given("^Back Office is configured to receive the taps from Terminal$")
    public void backOfficeIsConfiguredToReceiveTheTapsFromTerminal() {
        actionwords.backOfficeIsConfiguredToReceiveTheTapsFromTerminal();
    }


    @When("^\"(.*)\" is tapped on the Terminal \"(.*)\"$")
    public void cardSchemeIsTappedOnTheTerminal(String cardScheme, String param2) {
        actionwords.cardSchemeIsTappedOnTheTerminal(cardScheme, param2);
    }

    @Then("^Tap is received at the Back Office with same last 4 digits of the \"(.*)\"$")
    public void tapIsReceivedAtTheBackOfficeWithSameLast4DigitsOfTheCardScheme(String cardScheme) {
        actionwords.tapIsReceivedAtTheBackOfficeWithSameLast4DigitsOfTheCardScheme(cardScheme);
    }

    @Then("^a tap is generated$")
    public void aTapIsGenerated() {
        actionwords.aTapIsGenerated();
    }

    @Then("^Card Serial Number is captured from WhisperMon logs$")
    public void cardSerialNumberIsCapturedFromWhisperMonLogs() {
        actionwords.cardSerialNumberIsCapturedFromWhisperMonLogs();
    }

    @Then("^a PreAuth request is created in cut_emv_offline_payment table and sent to PEM$")
    public void aPreAuthRequestIsCreatedInCutEmvOfflinePaymentTableAndSentToPEM(String freeText) {
        actionwords.aPreAuthRequestIsCreatedInCutEmvOfflinePaymentTableAndSentToPEM(freeText);
    }

    @Then("^PEM receives PreAuth Approved response from Acquirer$")
    public void pEMReceivesPreAuthApprovedResponseFromAcquirer() {
        actionwords.pEMReceivesPreAuthApprovedResponseFromAcquirer();
    }

    @Given("^A Mastercard is available to generate the tap$")
    public void aMastercardIsAvailableToGenerateTheTap() {
        actionwords.aMastercardIsAvailableToGenerateTheTap();
    }

    @Given("^A Visa card is available to generate the tap$")
    public void aVisaCardIsAvailableToGenerateTheTap() {
        actionwords.aVisaCardIsAvailableToGenerateTheTap();
    }

    @When("^\"(.*)\" is tapped on the Terminal for the first time in the day$")
    public void cardSchemeIsTappedOnTheTerminalForTheFirstTimeInTheDay(String cardScheme) {
        actionwords.cardSchemeIsTappedOnTheTerminalForTheFirstTimeInTheDay(cardScheme);
    }

    @Given("^First tap of the Mastercard has been approved$")
    public void firstTapOfTheMastercardHasBeenApproved() {
        actionwords.firstTapOfTheMastercardHasBeenApproved();
    }

    @When("^Mastercard is tapped on the Terminal second time$")
    public void mastercardIsTappedOnTheTerminalSecondTime() {
        actionwords.mastercardIsTappedOnTheTerminalSecondTime();
    }

    @Then("^a PreAuth request is not created in cut_emv_offline_payment table$")
    public void aPreAuthRequestIsNotCreatedInCutEmvOfflinePaymentTable(String freeText) {
        actionwords.aPreAuthRequestIsNotCreatedInCutEmvOfflinePaymentTable(freeText);
    }

    @Given("^A \"(.*)\" is available to generate the tap$")
    public void aCardSchemeIsAvailableToGenerateTheTap(String cardScheme) {
        actionwords.aCardSchemeIsAvailableToGenerateTheTap(cardScheme);
    }

    @Then("^PEM receives PreAuth Declined response from Acquirer$")
    public void pEMReceivesPreAuthDeclinedResponseFromAcquirer() {
        actionwords.pEMReceivesPreAuthDeclinedResponseFromAcquirer();
    }

    @Then("^Visa card is added to the denied list$")
    public void visaCardIsAddedToTheDeniedList() {
        actionwords.visaCardIsAddedToTheDeniedList();
    }

    @Given("^Whisper CS is configured to add declined card to denied list$")
    public void whisperCSIsConfiguredToAddDeclinedCardToDeniedList() {
        actionwords.whisperCSIsConfiguredToAddDeclinedCardToDeniedList();
    }

    @Given("^A Visa card that is not in DenyList is available$")
    public void aVisaCardThatIsNotInDenyListIsAvailable() {
        actionwords.aVisaCardThatIsNotInDenyListIsAvailable();
    }

    @When("^BackOffice sends a request to add the card on DenyList$")
    public void backOfficeSendsARequestToAddTheCardOnDenyList() {
        actionwords.backOfficeSendsARequestToAddTheCardOnDenyList();
    }

    @Then("^card is added to DenyList$")
    public void cardIsAddedToDenyList() {
        actionwords.cardIsAddedToDenyList();
    }

    @Then("^card is removed from the DenyList$")
    public void cardIsRemovedFromTheDenyList() {
        actionwords.cardIsRemovedFromTheDenyList();
    }

    @When("^BackOffice sends a request to remove the card from DenyList$")
    public void backOfficeSendsARequestToRemoveTheCardFromDenyList() {
        actionwords.backOfficeSendsARequestToRemoveTheCardFromDenyList();
    }

    @Then("^a Status List Update message is sent to Backoffice$")
    public void aStatusListUpdateMessageIsSentToBackoffice() {
        actionwords.aStatusListUpdateMessageIsSentToBackoffice();
    }

    @Given("^A Visa card that is not in PilotList is available$")
    public void aVisaCardThatIsNotInPilotListIsAvailable() {
        actionwords.aVisaCardThatIsNotInPilotListIsAvailable();
    }

    @When("^BackOffice sends a request to add the card on PilotList$")
    public void backOfficeSendsARequestToAddTheCardOnPilotList() {
        actionwords.backOfficeSendsARequestToAddTheCardOnPilotList();
    }

    @Then("^card is added to PilotList$")
    public void cardIsAddedToPilotList() {
        actionwords.cardIsAddedToPilotList();
    }

    @When("^BackOffice sends a request to remove the card from PilotList$")
    public void backOfficeSendsARequestToRemoveTheCardFromPilotList() {
        actionwords.backOfficeSendsARequestToRemoveTheCardFromPilotList();
    }

    @Then("^card is removed from the PilotList$")
    public void cardIsRemovedFromThePilotList() {
        actionwords.cardIsRemovedFromThePilotList();
    }

    @Given("^Two taps marking end of a journey are received by BackOffice$")
    public void twoTapsMarkingEndOfAJourneyAreReceivedByBackOffice() {
        actionwords.twoTapsMarkingEndOfAJourneyAreReceivedByBackOffice();
    }

    @Given("^BackOffice calculates fares$")
    public void backOfficeCalculatesFares() {
        actionwords.backOfficeCalculatesFares();
    }

    @When("^BackOffice sends the fundsreqest of Amount against tapped card \"(.*)\"$")
    public void backOfficeSendsTheFundsreqestOfAmountAgainstTappedCard(String amount) {
        actionwords.backOfficeSendsTheFundsreqestOfAmountAgainstTappedCard(amount);
    }

    @Then("^Whisper receives the  funds request against the card$")
    public void whisperReceivesTheFundsRequestAgainstTheCard() {
        actionwords.whisperReceivesTheFundsRequestAgainstTheCard();
    }

    @Given("^BackOffice has send a FundRequest of 10\\.50 for a visa card$")
    public void backOfficeHasSendAFundRequestOf1050ForAVisaCard() {
        actionwords.backOfficeHasSendAFundRequestOf1050ForAVisaCard();
    }

    @When("^End of Period Script is run$")
    public void endOfPeriodScriptIsRun() {
        actionwords.endOfPeriodScriptIsRun();
    }

    @Then("^an End of Period Authorisation request with Authorisation amount 10\\.50 is sent for the card$")
    public void anEndOfPeriodAuthorisationRequestWithAuthorisationAmount1050IsSentForTheCard() {
        actionwords.anEndOfPeriodAuthorisationRequestWithAuthorisationAmount1050IsSentForTheCard();
    }

    @Given("^an Inspection tap is sent to BackOffice$")
    public void anInspectionTapIsSentToBackOffice() {
        actionwords.anInspectionTapIsSentToBackOffice();
    }


    @When("^BackOffice sends a Penalty request of amount \\$ 13\\.50 for a visa card$")
    public void backOfficeSendsAPenaltyRequestOfAmount1350ForAVisaCard() {
        actionwords.backOfficeSendsAPenaltyRequestOfAmount1350ForAVisaCard();
    }

    @Then("^Whisper creates a Penalty Auth request$")
    public void whisperCreatesAPenaltyAuthRequest() {
        actionwords.whisperCreatesAPenaltyAuthRequest();
    }

    @Then("^Bank approves Penalty Auth request$")
    public void bankApprovesPenaltyAuthRequest() {
        actionwords.bankApprovesPenaltyAuthRequest();
    }

    @Then("^Settlement request of amount \\$ 13\\.50 is sent$")
    public void settlementRequestOfAmount1350IsSent() {
        actionwords.settlementRequestOfAmount1350IsSent();
    }

    @Then("^Whisper creates a Penalty Auth request of \\$ 13\\.05$")
    public void whisperCreatesAPenaltyAuthRequestOf1305() {
        actionwords.whisperCreatesAPenaltyAuthRequestOf1305();
    }

    @When("^BackOffice sends a Penalty request of amount \\$ 13\\.05 for a visa card$")
    public void backOfficeSendsAPenaltyRequestOfAmount1305ForAVisaCard() {
        actionwords.backOfficeSendsAPenaltyRequestOfAmount1305ForAVisaCard();
    }

    @Then("^Bank declines Penalty Auth request$")
    public void bankDeclinesPenaltyAuthRequest() {
        actionwords.bankDeclinesPenaltyAuthRequest();
    }

    @Then("^\\$ 13\\.05 is added to the Debt$")
    public void 1305IsAddedToTheDebt() {
        actionwords.1305IsAddedToTheDebt();
    }

    @Given("^BackOffice determines to send Refund to a card$")
    public void backOfficeDeterminesToSendRefundToACard() {
        actionwords.backOfficeDeterminesToSendRefundToACard();
    }

    @Given("^BackOffice determines to charge a Penalty to a card$")
    public void backOfficeDeterminesToChargeAPenaltyToACard() {
        actionwords.backOfficeDeterminesToChargeAPenaltyToACard();
    }

    @When("^BackOffice sends a Refund request of amount \\$ 14\\.50 for a visa card$")
    public void backOfficeSendsARefundRequestOfAmount1450ForAVisaCard() {
        actionwords.backOfficeSendsARefundRequestOfAmount1450ForAVisaCard();
    }

    @Then("^Settlement request of amount \\$ 14\\.50 is sent$")
    public void settlementRequestOfAmount1450IsSent() {
        actionwords.settlementRequestOfAmount1450IsSent();
    }

    @Then("^Whisper does not send the PreAuth request$")
    public void whisperDoesNotSendThePreAuthRequest() {
        actionwords.whisperDoesNotSendThePreAuthRequest();
    }

    @Given("^A card has pending debt of \\$18\\.60 and is on Deny List$")
    public void aCardHasPendingDebtOf1860AndIsOnDenyList() {
        actionwords.aCardHasPendingDebtOf1860AndIsOnDenyList();
    }

    @When("^Debt Recovery script runs$")
    public void debtRecoveryScriptRuns() {
        actionwords.debtRecoveryScriptRuns();
    }

    @Then("^Whisper creates a Debt Auth request of \\$18\\.60$")
    public void whisperCreatesADebtAuthRequestOf1860() {
        actionwords.whisperCreatesADebtAuthRequestOf1860();
    }

    @Then("^Bank approves Debt Auth request$")
    public void bankApprovesDebtAuthRequest() {
        actionwords.bankApprovesDebtAuthRequest();
    }

    @Then("^Settlement request of amount \\$ 18\\.60 is sent$")
    public void settlementRequestOfAmount1860IsSent() {
        actionwords.settlementRequestOfAmount1860IsSent();
    }

    @Then("^Whisper creates a Debt Auth request of \\$18\\.55$")
    public void whisperCreatesADebtAuthRequestOf1855() {
        actionwords.whisperCreatesADebtAuthRequestOf1855();
    }

    @Then("^Bank declines Debt Auth request$")
    public void bankDeclinesDebtAuthRequest() {
        actionwords.bankDeclinesDebtAuthRequest();
    }

    @Then("^card debt amount remains the same$")
    public void cardDebtAmountRemainsTheSame() {
        actionwords.cardDebtAmountRemainsTheSame();
    }

    @Given("^Whisper CS is configured to does not add declined card to denied list$")
    public void whisperCSIsConfiguredToDoesNotAddDeclinedCardToDeniedList() {
        actionwords.whisperCSIsConfiguredToDoesNotAddDeclinedCardToDeniedList();
    }

    @Then("^Visa card is not added to the denied list$")
    public void visaCardIsNotAddedToTheDeniedList() {
        actionwords.visaCardIsNotAddedToTheDeniedList();
    }

    @Then("^BO receives Funds Result message$")
    public void bOReceivesFundsResultMessage() {
        actionwords.bOReceivesFundsResultMessage();
    }
}