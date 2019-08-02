package com.dal.mycareer.emailengine;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class EmployerApprovalEmail implements IEmployerApprovalEmail {
	
	private static final String EMPLOYER_APPROVAL_SUBJECT = "employerApprovalSubject";
	private static final String PASSWORD2 = "&password";
	private static final String ORGANIZATION = "&organization";
	private static final String USERNAME = "&username";
	private static final String EMPLOYER_APPROVAL_EMAIL = "employerApprovalEmail";
	private static final String EMPLOYER_NAME = "&EmployerName";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void employerApprovalEmail(String email, String name, String company, String password, String username) {
		
		logger.debug("Employer email approval : START");
		
		EmailService sgEmailService = new SendGridEmailService();
		Properties propertyMap = PropertiesParser.getPropertyMap();
		String body = propertyMap.getProperty(EMPLOYER_APPROVAL_EMAIL)
				.replace(EMPLOYER_NAME, name).replace(USERNAME, email).replace(PASSWORD2, password)
				.replace(ORGANIZATION, company);
		sgEmailService.sendHTML(email, propertyMap.getProperty(EMPLOYER_APPROVAL_SUBJECT), body);
		
		logger.debug("Employer email approval : END");
	}

}
