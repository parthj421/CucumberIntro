package helperClass;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.junit.Assert;

import BaseUtil.BaseUtil;




public class helperClass extends BaseUtil{
	
	private BaseUtil base;
	
	public helperClass(BaseUtil base) {
		this.base = base;
	}
	
	//Gives tag values of EMV JSON
	public String getEmvTags( JSONObject json, String tagName) throws Throwable {
		
		String tagValue = null;
		
		tagValue = (String) json.query("/emv/"+ tagName);	

		//String emv4F =   (String) jsonObj.query("/emv/4F");
		//Example in case the Json has an Array (It is represented by /0 or /1 etc.)
		//String emv4F =   (String) jsonObj.query("/sequence/0/gps_x");		
		
		return tagValue;
	}
	
	
	//Reads the EMV text file and converts it in a JSON object
	public JSONObject getEmvJson (String emvScheme, int cardNumber) throws Throwable {
		
		JSONObject emvJson = new JSONObject();
		
		Scanner input = new Scanner (new File ("src/main/java/data/" + emvScheme + cardNumber + ".txt"));
		
		String emvDetail = null;
		String completeEMV = "" ;
		
		while (input.hasNext())
		{
			emvDetail = input.nextLine();
			completeEMV = completeEMV + emvDetail;
		}
		input.close();	
		
		System.out.println("Complete EMV Captured: " + completeEMV);	
		
		 emvJson = new JSONObject (completeEMV) ;		
		
		return emvJson;
	}
	
	
	//Validates the PEM log- B2 Token values
	public boolean validateB2Token (String PEMlog, JSONObject emvCard) throws Throwable {
		boolean B2validation = true;
		
		
		//B2:001 Matching with 0000
		Pattern pattern = Pattern.compile("\\[P063:B2:001\\] (.*?)\n");
		
		Matcher match = pattern.matcher(PEMlog);
		
		if (match.find())
		{
			String	P063B2001 = match.group(1);
			
			 if (!P063B2001.equalsIgnoreCase("0000")) {			 	
				 System.out.println("P063:B2:001 is not matching. Expected:0000 Actual:"+ P063B2001);
				 B2validation =false;						 
			 }
			//assertEquals("P063:B2:001 is not matching. Expected:0000 Actual:"+ P063B2001,"0000", P063B2001);			
			//B2validation =true;			
		}
		
		else {
			System.out.println("P063:B2:001 is not in PEM logs");
			B2validation =false;
		}
		
		
		//B2:002 Matching with Tag 9F27		
		Pattern pattern2 = Pattern.compile("\\[P063:B2:002\\] (.*?)\n");
		
		Matcher match2 = pattern2.matcher(PEMlog);
		
		if (match2.find())
		{
			String	P063B2002 = match2.group(1);
			
			String emv9F27 =  getEmvTags(emvCard,"9F27");
			
			 if (!P063B2002.equalsIgnoreCase(emv9F27)) {			 	
				 System.out.println("P063:B2:002 is not matching. Expected: " + emv9F27  +" Actual: "+ P063B2002);
				 B2validation =false;						 
			 }						
		}
		
		else {
			System.out.println("P063:B2:002 is not in PEM logs");
			B2validation =false;
		}
		
		
		//B2:003 Matching with Tag 95
		
		
		
		
		
		return B2validation;
	}



}
