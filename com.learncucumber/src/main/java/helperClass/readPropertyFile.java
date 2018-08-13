package helperClass;

import java.util.Properties;
import java.io.InputStream;


public class readPropertyFile {
	Properties props = new Properties();
	InputStream input =   readPropertyFile.class.getClassLoader().getResourceAsStream("/main/java/config/config.properties");
	

	
	

}
