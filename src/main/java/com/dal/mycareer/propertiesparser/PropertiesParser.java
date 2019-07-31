package com.dal.mycareer.propertiesparser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesParser {

	private static final ClassLoader CLASS_LOADER = MethodHandles.lookup().lookupClass().getClassLoader();
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static Properties prop;
	private static InputStream inputStream;

	static {
		
		try {
			prop = new Properties();
			String propFileName = "mycareer.properties";

			inputStream = CLASS_LOADER.getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

		} catch (IOException ex) {
			LOGGER.error("Error loading properties file : " + ex.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				LOGGER.error("Error closing input stream : " + e.getMessage());
			}
		}
	}

	public static Properties getPropertyMap() {
		return prop;
	}

}
