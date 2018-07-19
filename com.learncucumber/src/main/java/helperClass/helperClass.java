package helperClass;

import java.io.File;
import java.util.Scanner;

import org.json.JSONObject;

public class helperClass {
	
	
	public String getEmvTags( JSONObject json, String tagName) throws Throwable {
		
		String tagValue = null;
		
		tagValue = (String) json.query("/emv/"+ tagName);	

		//String emv4F =   (String) jsonObj.query("/emv/4F");
		//Example in case the Json has an Array (It is represented by /0 or /1 etc.)
		//String emv4F =   (String) jsonObj.query("/sequence/0/gps_x");		
		
		return tagValue;
	}
	
	
	
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

}
