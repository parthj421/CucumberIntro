package stepDefinition;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.junit.Assert;

import BaseUtil.BaseUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helperClass.helperClass;
import helperClass.readPropertyFile;



public class cucumberTest extends BaseUtil{
	
	
	private BaseUtil base;
	
	public cucumberTest(BaseUtil base) {
		this.base = base;
	}
	
	@Given("^Card details of (.*) (.*) (.*) is available$")
	public void card_details_of_BarclayCard_is_available(String cardScheme, String cardName, int cardNumber) throws Throwable {
		
		
		helperClass helpClass = new helperClass(base);
		
		JSONObject emvJson = helpClass.getEmvJson(cardName, cardNumber);
		
		base.emvCard = emvJson;
		base.cardScheme = cardScheme;
		base.cardNumber = Integer.toString(cardNumber);
		
		String emv9F03 = helpClass.getEmvTags(emvJson,"9F1A");
		
		System.out.println("Value of EMV-9F03 is: " + emv9F03);
	}
	
	
	@When("^Card is tapped on the FrontOffice validator$")
	public void caard_is_tapped_on_the_FrontOffice_validator() throws Throwable {
		
		readPropertyFile prop = new readPropertyFile(base);
		prop.getPropertyValue();

	}

	@Then("^A tap should happen$")
	public void a_tap_should_happen() throws Throwable {

	}

	@Then("^(.*?) PEM logs should match with the expected data$")
	public void pre_Auth_PEM_logs_should_match_with_the_expected_data(String authRequestType) throws Throwable {
				
		String pem = null;
		String completePEM = "" ;
		base.requestType = authRequestType;
		
		Scanner input = new Scanner (new File ("src/main/java/data/PEMLog_BarclayCard" + base.cardNumber + ".txt"));
		
		while (input.hasNext())
		{
			pem = input.nextLine();
			completePEM = completePEM + pem + "\n";
		}
		
		input.close();	
		
		//System.out.println("Complete PEM File: " + completePEM);
		
		base.PEMlog = completePEM;
		
		//Pattern pattern = Pattern.compile("/pemauthemv(.*?) \\(D\\)");  TO ESCAPE the () in Regular Expression
		
		
		helperClass helpClass = new helperClass(base);
		
		boolean B2Status = helpClass.validateB2Token(completePEM, base.emvCard);

		boolean B3Status = helpClass.validateB3Token(completePEM, base.emvCard);
		
		boolean B4Status = helpClass.validateB4Token(completePEM, base.emvCard);
		
		boolean C4Status = helpClass.validateC4Token(completePEM, base.emvCard);
		
		boolean authReqStatus = helpClass.validateAuthRequest(completePEM, base.emvCard);
		
		Assert.assertTrue("B2 Token is not valid", B2Status);
		Assert.assertTrue("B3 Token is not valid", B3Status);
		Assert.assertTrue("B4 Token is not valid", B4Status);	
		Assert.assertTrue("C4 Token is not valid", C4Status);
		Assert.assertTrue("Auth Request is not valid", authReqStatus);
		
		if(completePEM.contains("TOKEN QE")) {
			boolean QEStatus = helpClass.validateQEToken(completePEM, base.emvCard);
			Assert.assertTrue("QE Token is not valid", QEStatus);
		}
	}

}
