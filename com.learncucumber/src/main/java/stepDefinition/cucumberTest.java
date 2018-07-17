package stepDefinition;

import java.io.File;
import java.util.Scanner;

import org.json.JSONObject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class cucumberTest {
	
	
	
	@Given("^Card details of BarclayCard (.*) is available$")
	public void card_details_of_BarclayCard_is_available(int cardNumber) throws Throwable {
		
		Scanner input = new Scanner (new File ("src/main/java/data/Bcard" + cardNumber + ".txt"));
		
		String emvDetail = null;
		String completeEMV = "" ;
		
		while (input.hasNext())
		{
			emvDetail = input.nextLine();
			System.out.println(emvDetail);
			
			completeEMV = completeEMV + emvDetail;
		}
		input.close();	
		
		//String parsedEmvDetails =  emvDetail.replace('"', '\"');
		System.out.println("Complete EMV Captured: " + completeEMV);
		
		
		JSONObject jsonObj = new JSONObject (completeEMV) ;
		
		String emv4F =  jsonObj.getString("emv.57");
		//String emv4F =  jsonObj.toString();	
		System.out.println("Value if EMV:4F is: " + emv4F);
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
