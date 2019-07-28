package com.dal.mycareer.emailengine;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class EmployerApprovalEmail implements IEmployerApprovalEmail {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void employerApprovalEmail(String email, String name, String company, String password, String username) {
		
		logger.debug("Employer email approval : START");
		
		EmailService sgEmailService = new SendGridEmailService();
		Properties propertyMap = PropertiesParser.getPropertyMap();
		String body = propertyMap.getProperty("employerApprovalEmail")
				.replace("&EmployerName", name).replace("&username", email).replace("&password", password)
				.replace("&organization", company);
		sgEmailService.sendHTML(email, propertyMap.getProperty("employerApprovalSubject"), body);
		
		logger.debug("Employer email approval : END");
	}

}
