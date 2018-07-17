package stepDefinition;

import java.io.File;
import java.util.Scanner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class cucumberTest {
	
	
	
	@Given("^Card details of BarclayCard (.*) is available$")
	public void card_details_of_BarclayCard_is_available(int cardNumber) throws Throwable {
		
		Scanner input = new Scanner (new File ("Bcard1.txt"));
		
		System.out.println(input);

	}

	@When("^Card is tapped on the FrontOffice validator$")
	public void caard_is_tapped_on_the_FrontOffice_validator() throws Throwable {

	}

	@Then("^A tap should happen$")
	public void a_tap_should_happen() throws Throwable {

	}

	@Then("^Pre-Auth PEM logs should match with the expected data$")
	public void pre_Auth_PEM_logs_should_match_with_the_expected_data() throws Throwable {

	}

}
