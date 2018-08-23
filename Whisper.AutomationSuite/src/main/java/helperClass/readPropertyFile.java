package helperClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import BaseUtil.BaseUtil;

public class readPropertyFile extends BaseUtil {

	private BaseUtil base;

	public  readPropertyFile(BaseUtil base) {
		this.base = base;
	}
	
	Properties property = new Properties();
	
	public void getPropertyValue() throws IOException {
	
		InputStream input =   readPropertyFile.class.getClassLoader().getResourceAsStream("config/config.properties");
		
		property.load(input);
		
		base.partialURL = property.getProperty("url");
		
		System.out.println(base.partialURL);
		
	}
	
	

	
	

}
