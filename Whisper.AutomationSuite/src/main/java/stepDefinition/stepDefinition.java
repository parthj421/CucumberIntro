package stepDefinition;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.junit.Assert;

import BaseUtil.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helperClass.helperClass;
import helperClass.readPropertyFile;



public class stepDefinition extends BaseUtil{
	
	
	private BaseUtil base;
	
	public stepDefinition(BaseUtil base) {
		this.base = base;
	}
	
    @Before
    public void beforeCallingScenario(Scenario sc) 
    {
        System.out.println("\n=======================================================================================================\n");
        System.out.println("***** ----- " + sc.getName());
        System.out.println("\n=======================================================================================================\n");        
    }
	
    
    @After
    public void afterCallingScenario(Scenario sc) 
    {
    	System.out.println("\n=======================================================================================================\n");
    	/*System.out.println("***** ----- " + (base.testStatus ? "PASS" : "FAIL") + " ----- *****");*/
    	System.out.println("***** ----- End of " + sc.getName()  + "  ----- *****");
    	System.out.println("\n=======================================================================================================\n");
    }
    
    
	@Given("^Card details of (.*) (.*) (.*) is available$")
	public void card_details_of_BarclayCard_is_available(String cardScheme, String cardName, int cardNumber) throws Throwable {
		
		helperClass helpClass = new helperClass(base);
		
		JSONObject emvJson = helpClass.getEmvJson(cardName, cardNumber);
		
		base.emvCard = emvJson;
		base.cardScheme = cardScheme;
		base.cardNumber = Integer.toString(cardNumber);
		base.cardName = cardName;
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
		
		Scanner input = new Scanner (new File ("src/main/java/data/PEMLog_" + base.cardName  + base.cardNumber + "_" + base.requestType + ".txt"));
		
		while (input.hasNext())
		{
			pem = input.nextLine();
			completePEM = completePEM + pem + "\n";
		}
		
		input.close();	
		
		base.PEMlog = completePEM;
		
		//Pattern pattern = Pattern.compile("/pemauthemv(.*?) \\(D\\)");  TO ESCAPE the () in Regular Expression
		
		helperClass helpClass = new helperClass(base);
		
		boolean C4Status = helpClass.validateC4Token(completePEM, base.emvCard);
		boolean authReqStatus = helpClass.validateAuthRequest(completePEM, base.emvCard);
		boolean C0Status = helpClass.validateC0Token(completePEM, base.emvCard);
		boolean B2Status = helpClass.validateB2Token(completePEM, base.emvCard);
		boolean B3Status = helpClass.validateB3Token(completePEM, base.emvCard);	
		boolean B4Status = helpClass.validateB4Token(completePEM, base.emvCard);
		boolean F3Status = helpClass.validateF3Token(completePEM, base.emvCard);
		
		if(completePEM.contains("TOKEN QE")) {
			boolean QEStatus = helpClass.validateQEToken(completePEM, base.emvCard);
			Assert.assertTrue("QE Token is not valid", QEStatus);
		}
		
		Assert.assertTrue("B2 Token is not valid", B2Status);
		Assert.assertTrue("B3 Token is not valid", B3Status);
		Assert.assertTrue("B4 Token is not valid", B4Status);
		Assert.assertTrue("F3 Token is not valid", F3Status);
		Assert.assertTrue("C0 Token is not valid", C0Status);
		Assert.assertTrue("C4 Token is not valid", C4Status);		
		Assert.assertTrue("Auth Request is not valid", authReqStatus);
		

	}

}
