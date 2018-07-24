package stepDefinition;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helperClass.helperClass;



public class cucumberTest {
	
	
	
	@Given("^Card details of (.*) (.*) is available$")
	public void card_details_of_BarclayCard_is_available(String cardScheme, int cardNumber) throws Throwable {
		
		
		helperClass helpClass = new helperClass();
		
		JSONObject emvJson = helpClass.getEmvJson(cardScheme, cardNumber);
		
		String emv4F = helpClass.getEmvTags(emvJson, "4F");
		
		String emv9F03 = helpClass.getEmvTags(emvJson,"9F1A");
		
		System.out.println("Value of EMV-9F03 is: " + emv9F03);
	}
	
	
	@When("^Card is tapped on the FrontOffice validator$")
	public void caard_is_tapped_on_the_FrontOffice_validator() throws Throwable {

	}

	@Then("^A tap should happen$")
	public void a_tap_should_happen() throws Throwable {

	}

	@Then("^Pre-Auth PEM logs should match with the expected data$")
	public void pre_Auth_PEM_logs_should_match_with_the_expected_data() throws Throwable {
		
		
		Scanner input = new Scanner (new File ("src/main/java/data/PEMLog_BarclayCard1.txt"));
		
		String pem = null;
		String completePEM = "" ;
		
		while (input.hasNext())
		{
			pem = input.nextLine();
			completePEM = completePEM + pem + "\n";
		}
		
		input.close();	
		
//		System.out.println("Complete PEM File: " + completePEM);
		
		
		//Pattern pattern = Pattern.compile("/pemauthemv(.*?) \\(D\\)");  TO ESCAPE the () in Regular Expression
		Pattern pattern = Pattern.compile("\\[P063:B2:002\\] (.*?)\n");
		
		Matcher match = pattern.matcher(completePEM);
		
		if (match.find())
		{
			System.out.println("Matching Value: " +  match.group(1));
		}
		
		else {
			System.out.println("No Match Found");
		}
			

	}

}
