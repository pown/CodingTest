package common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {
	
	
	public  static Properties loadProperties(String filePath) {
		Properties properties = new Properties();
		try{
		File propertyFile = new File(filePath);
		properties.load(new FileInputStream(propertyFile));
		}
		catch(IOException e)
		{
		 e.printStackTrace();
		}
		

		
		return properties;
		
	}
	
	public static String getPropertyValue(Properties properties, String propertyName) 
	{	
		String propertyValue = "";
		if(properties.containsKey(propertyName)){
			propertyValue = properties.getProperty(propertyName).trim();
		}

		return propertyValue;

	}
	

}
