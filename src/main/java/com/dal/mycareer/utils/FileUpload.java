package com.dal.mycareer.utils;

import java.lang.invoke.MethodHandles;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class FileUpload {
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	Properties dbProps = PropertiesParser.getPropertyMap();
	private final String fileUploadPath = dbProps.getProperty("fileUploadPath");
			
	
	
}
