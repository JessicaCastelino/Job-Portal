package com.dal.mycareer.utils;

import java.lang.invoke.MethodHandles;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class FileUpload {
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	Properties dbProps = PropertiesParser.getPropertyMap();
	private final String fileUploadPath = dbProps.getProperty("fileUploadPath");
			
	
	
}
