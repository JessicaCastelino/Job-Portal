package com.dal.mycareer.emailengine;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class EmployerRejectionEmailImpl implements IEmployerRejectionEmail {
	
	private static final String EMPLOYER_REJECTION_SUBJECT = "employerRejectionSubject";
	private static final String EMPLOYER_REJECTION_EMAIL = "employerRejectionEmail";
	private static final String EMPLOYER_NAME = "&EmployerName";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void employerRejectionEmail(String email, String name, String company) {
		
		logger.debug("Employer rejection email : START");
		
		EmailService sgEmailService = new SendGridEmailService();
		Properties propertyMap = PropertiesParser.getPropertyMap();
		String emailBody = propertyMap.getProperty(EMPLOYER_REJECTION_EMAIL)
				.replace(EMPLOYER_NAME, name);
		sgEmailService.sendHTML(email, propertyMap.getProperty(EMPLOYER_REJECTION_SUBJECT),
				emailBody);
		
		logger.debug("Employer rejection email : ENDS");
	}

}
