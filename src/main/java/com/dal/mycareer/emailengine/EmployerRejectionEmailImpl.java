package com.dal.mycareer.emailengine;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class EmployerRejectionEmailImpl implements IEmployerRejectionEmail {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void employerRejectionEmail(String email, String name, String company) {
		
		logger.debug("Employer rejection email : START");
		
		EmailService sgEmailService = new SendGridEmailService();
		Properties propertyMap = PropertiesParser.getPropertyMap();
		String emailBody = propertyMap.getProperty("employerRejectionEmail")
				.replace("&EmployerName", name);
		sgEmailService.sendHTML(email, propertyMap.getProperty("employerRejectionSubject"),
				emailBody);
		
		logger.debug("Employer rejection email : ENDS");
	}

}
