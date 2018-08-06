package helperClass;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.junit.Assert;

import BaseUtil.BaseUtil;

public class helperClass extends BaseUtil {

	private BaseUtil base;

	public helperClass(BaseUtil base) {
		this.base = base;
	}

	// Gives tag values of EMV JSON
	public String getEmvTags(JSONObject json, String tagName) throws Throwable {

		String tagValue = "";

		tagValue = (String) json.query("/emv/" + tagName);

		// String emv4F = (String) jsonObj.query("/emv/4F");
		// Example in case the Json has an Array (It is represented by /0 or /1 etc.)
		// String emv4F = (String) jsonObj.query("/sequence/0/gps_x");

		return tagValue;
	}

	// Reads the EMV text file and converts it in a JSON object
	public JSONObject getEmvJson(String emvScheme, int cardNumber) throws Throwable {

		JSONObject emvJson = new JSONObject();

		Scanner input = new Scanner(new File("src/main/java/data/" + emvScheme + cardNumber + ".txt"));

		String emvDetail = null;
		String completeEMV = "";

		while (input.hasNext()) {
			emvDetail = input.nextLine();
			completeEMV = completeEMV + emvDetail;
		}
		input.close();

		System.out.println("Complete EMV Captured: " + completeEMV);

		emvJson = new JSONObject(completeEMV);

		return emvJson;
	}

	// hardCode variable determines if emvTag value is hardCoded or need to be
	// extracted. ( Acceptable values: Y|N)
	public boolean comparePEMwithEMVTag(String pemTag, String emvTag, String hardCode) {

		boolean isMatch = true;

		// Pattern Matcher is used to implement Regular Expression in a given string
		// Pattern pattern = Pattern.compile("/pemauthemv(.*?) \\(D\\)"); TO ESCAPE the
		// () in Regular Expression
		// Pattern pattern = Pattern.compile("\\[P063:B2:002\\] (.*?)\n");

		Pattern pattern = Pattern.compile("\\[" + pemTag + "\\] (.*?)\n");

		Matcher match = pattern.matcher(base.PEMlog);

		if (match.find()) {
			String pemTagValue = match.group(1);

			String emvTagValue = "";
			try {
				emvTagValue = hardCode == "Y" ? emvTag : getEmvTags(base.emvCard, emvTag);
			} catch (Throwable e) {

				e.printStackTrace();
			}

			if (!pemTagValue.equalsIgnoreCase(emvTagValue)) {
				System.out.println(pemTag + " is not matching with " + emvTag + ". Expected: " + emvTagValue
						+ " Actual: " + pemTagValue);
				// assertEquals("P063:B2:001 is not matching. Expected:0000 Actual:"+
				// pemTagValue ,emvTagValue, pemTagValue);
				isMatch = false;
			}
		}

		else {
			System.out.println(pemTag + " is not in PEM logs");
			isMatch = false;
		}

		return isMatch;

	}

	public String getPEMTag(String pemTag) {

		String pemTagValue = "";

		Pattern pattern = Pattern.compile("\\[" + pemTag + "\\] (.*?)\n");

		Matcher match = pattern.matcher(base.PEMlog);

		if (match.find()) {
			pemTagValue = match.group(1);
		}
		return pemTagValue;
	}

	public boolean comparePEMwithPEMTag(String pemTag1, String pemTag2) {

		boolean isMatch = true;

		Pattern pattern1 = Pattern.compile("\\[" + pemTag1 + "\\] (.*?)\n");

		Matcher match1 = pattern1.matcher(base.PEMlog);

		if (match1.find()) {
			String pemTagValue1 = match1.group(1);

			Pattern pattern2 = Pattern.compile("\\[" + pemTag1 + "\\] (.*?)\n");

			Matcher match2 = pattern2.matcher(base.PEMlog);

			if (match2.find()) {
				String pemTagValue2 = match1.group(1);

				if (!pemTagValue1.equalsIgnoreCase(pemTagValue2)) {
					System.out.println(pemTag1 + " is not matching with " + pemTag2);
					isMatch = false;
				}
			}

			else {
				System.out.println(pemTag2 + " is not in PEM logs");
				isMatch = false;
			}
		}

		else {
			System.out.println(pemTag1 + " is not in PEM logs");
			isMatch = false;
		}
		return isMatch;
	}

	// biggerString variable determines which string will contain the other string (
	// Acceptable values: PEM|EMV)

	public boolean PEMcontainsEMVTag(String pemTag, String emvTag, String biggerString) {
		boolean isMatch = true;

		Pattern pattern = Pattern.compile("\\[" + pemTag + "\\] (.*?)\n");

		Matcher match = pattern.matcher(base.PEMlog);

		if (match.find()) {
			String pemTagValue = match.group(1);

			String emvTagValue = "";

			try {
				emvTagValue = getEmvTags(base.emvCard, emvTag);
			} catch (Throwable e) {

				e.printStackTrace();
			}

			if (biggerString.equalsIgnoreCase("PEM")) {

				if (!pemTagValue.contains(emvTagValue)) {
					System.out.println(pemTag + " is not containing " + emvTag + ". Expected: " + emvTagValue
							+ " Actual: " + pemTagValue);
					// assertEquals("P063:B2:001 is not matching. Expected:0000 Actual:"+
					// pemTagValue ,emvTagValue, pemTagValue);
					isMatch = false;
				}

			}

			else if (biggerString.equalsIgnoreCase("EMV")) {

				if (!emvTagValue.contains(pemTagValue)) {
					System.out.println(emvTag + " is not containing " + pemTag + ". Expected: " + emvTagValue
							+ " Actual: " + pemTagValue);
					// assertEquals("P063:B2:001 is not matching. Expected:0000 Actual:"+
					// pemTagValue ,emvTagValue, pemTagValue);
					isMatch = false;
				}
			}
		}

		else {
			System.out.println(pemTag + " is not in PEM logs");
			isMatch = false;
		}

		return isMatch;

	}

	// Validates the PEM log- B2 Token values
	public boolean validateB2Token(String PEMlog, JSONObject emvCard) throws Throwable {
		boolean B2validation = true;

		// B2:001 Matching with "0000"
		B2validation = comparePEMwithEMVTag("P063:B2:001", "0000", "Y") & B2validation;
		// B2:002 Matching with Tag 9F27
		B2validation = comparePEMwithEMVTag("P063:B2:002", "9F27", "N") & B2validation;
		// B2:003 Matching with Tag 95
		B2validation = comparePEMwithEMVTag("P063:B2:003", "95", "N") & B2validation;
		// B2:004 Matching with Tag 9F26
		B2validation = comparePEMwithEMVTag("P063:B2:004", "9F26", "N") & B2validation;
		// B2:005 Matching with Tag 9F02
		B2validation = comparePEMwithEMVTag("P063:B2:005", "9F02", "N") & B2validation;
		// B2:006 Matching with Tag 9F03
		B2validation = comparePEMwithEMVTag("P063:B2:006", "9F03", "N") & B2validation;
		// B2:007 Matching with Tag 82
		B2validation = comparePEMwithEMVTag("P063:B2:007", "82", "N") & B2validation;
		// B2:008 Matching with Tag 9F36
		B2validation = comparePEMwithEMVTag("P063:B2:008", "9F36", "N") & B2validation;
		// B2:009 contained in Tag 9F1A
		B2validation = PEMcontainsEMVTag("P063:B2:009", "9F1A", "EMV") & B2validation;
		// B2:010 Matching with PEM Tag B2:009
		B2validation = comparePEMwithPEMTag("P063:B2:010", "P063:B2:009") & B2validation;
		// B2:011 Matching with Transaction date
		// B2:012 Matching with EMV Tag 9C
		B2validation = comparePEMwithEMVTag("P063:B2:012", "9C", "N") & B2validation;
		// B2:013 Matching with EMV Tag 9F37
		B2validation = comparePEMwithEMVTag("P063:B2:013", "9F37", "N") & B2validation;
		// B2:016 contains with EMV Tag 9F10
		B2validation = PEMcontainsEMVTag("P063:B2:016", "9F10", "PEM") & B2validation;

		return B2validation;
	}

	public boolean validateB3Token(String PEMlog, JSONObject emvCard) throws Throwable {

		boolean B3validation = true;

		// B3:001 Matching with Terminal Id
		// B3:002 Matching with "00000808"
		B3validation = comparePEMwithEMVTag("P063:B3:002", "00000808", "Y") & B3validation;
		// B3:003 Matching with "0000"
		B3validation = comparePEMwithEMVTag("P063:B3:003", "0000", "Y") & B3validation;
		// B3:004 Matching with "00000000"
		B3validation = comparePEMwithEMVTag("P063:B3:004", "00000000", "Y") & B3validation;
		// B3:005 Matching with EMV tag 9F35
		B3validation = comparePEMwithEMVTag("P063:B3:005", "9F35", "N") & B3validation;
		// B3:006 Matching with EMV tag 9F09
		B3validation = comparePEMwithEMVTag("P063:B3:006", "9F09", "N") & B3validation;
		// B3:007 Matching with EMV tag 9F34
		B3validation = comparePEMwithEMVTag("P063:B3:007", "9F34", "N") & B3validation;
		// B3:008 contains with EMV tag 84
		B3validation = PEMcontainsEMVTag("P063:B3:008", "84", "PEM") & B3validation;

		return B3validation;
	}

	public boolean validateB4Token(String PEMlog, JSONObject emvCard) throws Throwable {

		boolean B4validation = true;

		// P063:B4:000 is same as P022 of PEM Request
		B4validation = comparePEMwithPEMTag("P063:B4:000", "P022") & B4validation;
		// P063:B4:001 is same as "8"
		B4validation = comparePEMwithEMVTag("P063:B4:001", "8", "Y") & B4validation;
		// P063:B4:002 is same as "1"
		B4validation = comparePEMwithEMVTag("P063:B4:002", "1", "Y") & B4validation;
		// P063:B4:003 is same as "0"
		B4validation = comparePEMwithEMVTag("P063:B4:003", "0", "Y") & B4validation;
		// P063:B4:004 is same as EMV Tag 5F34
		B4validation = comparePEMwithEMVTag("P063:B4:004", "5F34", "N") & B4validation;
		// P063:B4:007 is same as "1506"
		B4validation = comparePEMwithEMVTag("P063:B4:007", "1506", "Y") & B4validation;
		// P063:B4:008 is same as "0"
		B4validation = comparePEMwithEMVTag("P063:B4:008", "0", "Y") & B4validation;
		// P063:B4:009 is same as "0"
		B4validation = comparePEMwithEMVTag("P063:B4:009", "0", "Y") & B4validation;

		return B4validation;
	}

	public boolean validateC4Token(String PEMlog, JSONObject emvCard) throws Throwable {

		boolean C4validation = true;

		// P063:C4:000 is same as "1"
		C4validation = comparePEMwithEMVTag("P063:C4:000", "1", "Y") & C4validation;
		// P063:C4:001 is same as "0"
		C4validation = comparePEMwithEMVTag("P063:C4:001", "0", "Y") & C4validation;
		// P063:C4:002 is same as "0"
		C4validation = comparePEMwithEMVTag("P063:C4:002", "0", "Y") & C4validation;
		// P063:C4:003 is same as "0"
		C4validation = comparePEMwithEMVTag("P063:C4:003", "0", "Y") & C4validation;
		// P063:C4:004 is same as "0"
		C4validation = comparePEMwithEMVTag("P063:C4:004", "0", "Y") & C4validation;
		// P063:C4:005 is same as "0"
		C4validation = comparePEMwithEMVTag("P063:C4:005", "0", "Y") & C4validation;

		// P063:C4:006 is "9" for Visa/MasterCard Account Verification(PreAuth) Request, "0" for Visa/MasterCard EODAuth Request

		switch (base.requestType.toLowerCase()) {
		case "preauth":
			C4validation = comparePEMwithEMVTag("P063:C4:006", "9", "Y") & C4validation;
			break;
		case "eodauth":
			C4validation = comparePEMwithEMVTag("P063:C4:006", "0", "Y") & C4validation;
			break;
		}

		// P063:C4:007 is same as "0"
		C4validation = comparePEMwithEMVTag("P063:C4:007", "0", "Y") & C4validation;

		// P063:C4:008 is 0 for Visa and "0" for PreAuth MasterCard and "9" for EODAuth Mastercard
		if (base.cardScheme.toLowerCase() == "visa") {
			C4validation = comparePEMwithEMVTag("P063:C4:008", "0", "Y") & C4validation;
		}

		else if (base.cardScheme.toLowerCase() == "mastercard") {
			switch (base.requestType.toLowerCase()) {
			case "preauth":
				C4validation = comparePEMwithEMVTag("P063:C4:008", "0", "Y") & C4validation;
				break;
			case "eodauth":
				C4validation = comparePEMwithEMVTag("P063:C4:008", "9", "Y") & C4validation;
				break;
			}
		}

		// P063:C4:009 is same as "2"
		C4validation = comparePEMwithEMVTag("P063:C4:009", "2", "Y") & C4validation;
		// P063:C4:010 is same as "3"
		C4validation = comparePEMwithEMVTag("P063:C4:010", "3", "Y") & C4validation;
		// P063:C4:011 is same as "3"
		C4validation = comparePEMwithEMVTag("P063:C4:011", "3", "Y") & C4validation;
		return C4validation;
	}

	public boolean validateQEToken(String PEMlog, JSONObject emvCard) throws Throwable {

		boolean QEvalidation = true;

		// P063:QE:000 is same as "9F6E"
		QEvalidation = comparePEMwithEMVTag("P063:QE:000", "9F6E", "Y") & QEvalidation;
		// P063:QE:001 is same as EMV 9F6E tag length
		String tag9F6E = getEmvTags(base.emvCard, "9F6E");
		String pemQE001 = getPEMTag("P063:QE:001");
		QEvalidation = Integer.parseInt(pemQE001) == tag9F6E.length() / 2 & QEvalidation;

		// P063:QE:002 is same as EMV 9F6E tag
		QEvalidation = PEMcontainsEMVTag("P063:QE:002", "9F6E", "PEM") & QEvalidation;
		// P063:QE:003 is same as "9F7C"
		QEvalidation = comparePEMwithEMVTag("P063:QE:003", "9F7C", "Y") & QEvalidation;

		// P063:QE:004 is same as EMV 9F7C tag length (This will be uncommented after 9F7C in added on the EMV Tag)
		/*
		 * String tag9F7C = getEmvTags(base.emvCard,"9F7C"); String pemQE004 =
		 * getPEMTag("P063:QE:004"); QEvalidation = Integer.parseInt(pemQE004) ==
		 * tag9F7C.length()/2 & QEvalidation;
		 */

		// P063:QE:005 is same as EMV 9F7C tag (This will be uncommented after 9F7C in added on the EMV Tag)
		// QEvalidation = PEMcontainsEMVTag("P063:QE:005", "9F7C","PEM") & QEvalidation;

		// P063:QE:006 is same as "N"
		QEvalidation = comparePEMwithEMVTag("P063:QE:006", "N", "Y") & QEvalidation;

		// If first two bits of 9F34 is 01 then P063:QE:006 is same as "Y" else "N"
		String tag9F34 = getEmvTags(base.emvCard, "9F34");
		QEvalidation = tag9F34.substring(0, 2) == "01" ? comparePEMwithEMVTag("P063:QE:007", "Y", "Y")
				: comparePEMwithEMVTag("P063:QE:007", "N", "Y") & QEvalidation;

		return QEvalidation;
	}

	public boolean validateAuthRequest(String PEMlog, JSONObject emvCard) throws Throwable {

		boolean authReqValidation = true;

		// P000 is same as "0100"
		authReqValidation = comparePEMwithEMVTag("P000", "0100", "Y") & authReqValidation;
		// P002 is same as Emv tag 5A
		authReqValidation = comparePEMwithEMVTag("P002", "5A", "N") & authReqValidation;

		// P003 is same as "81000" for PreAuth, "000000" for EODAuth and "200000" for
		// refund
		switch (base.requestType.toLowerCase()) {
		case "preauth":
			authReqValidation = comparePEMwithEMVTag("P003", "810000", "Y") & authReqValidation;
			break;
		case "eodauth":
			authReqValidation = comparePEMwithEMVTag("P003", "000000", "Y") & authReqValidation;
			break;
		case "refund":
			authReqValidation = comparePEMwithEMVTag("P003", "200000", "Y") & authReqValidation;
			break;
		}

		// P004 is same as transaction amount
		switch (base.requestType.toLowerCase()) {
		case "preauth":
			authReqValidation = comparePEMwithEMVTag("P004", "000000000000", "Y") & authReqValidation;
			break;
		case "eodauth":
			authReqValidation = comparePEMwithEMVTag("P004", base.txnAmount, "Y") & authReqValidation;
			break;
		case "refund":
			authReqValidation = comparePEMwithEMVTag("P004", base.txnAmount, "Y") & authReqValidation;
			break;
		}

		// P007 is same txn Date and Time
		// P012 is same txn Time
		// P013 is same txn Date
		// P013 is same Exp Date

		// P022 is same as "072"
		authReqValidation = comparePEMwithEMVTag("P022", "072", "Y") & authReqValidation;
		// P023 is same as Emv Tag 5F34
		authReqValidation = PEMcontainsEMVTag("P023", "5F34", "PEM") & authReqValidation;

		// P025 is "27" for visa, "06" for Maestro/Mastercard

		switch (base.cardScheme.toLowerCase()) {
		case "visa":
			authReqValidation = comparePEMwithEMVTag("P025", "27", "Y") & authReqValidation;
			break;
		case "mastercard":
			authReqValidation = comparePEMwithEMVTag("P025", "06", "Y") & authReqValidation;
			break;
		case "masestro":
			authReqValidation = comparePEMwithEMVTag("P025", "06", "Y") & authReqValidation;
			break;

		}

		// P035 is same as emv tag 57
		authReqValidation = PEMcontainsEMVTag("P035", "57", "EMV") & authReqValidation;

		// P041 is same as Terminal id from the Request

		// P043 should be same as "Tfgm                  Manchester      GB"
		// authReqValidation = comparePEMwithEMVTag("P043", "Tfgm                  Manchester      GB","Y") &
		// authReqValidation;

		// P049 is same as 9F1A
		authReqValidation = PEMcontainsEMVTag("P049", "9F1A", "EMV") & authReqValidation;
		// P125 is same as 9F1A
		authReqValidation = comparePEMwithEMVTag("P125", "01200HOSTB24 00", "Y") & authReqValidation;

		return authReqValidation;
	}

}
