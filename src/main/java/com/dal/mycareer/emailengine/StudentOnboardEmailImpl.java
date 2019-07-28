package com.dal.mycareer.emailengine;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class StudentOnboardEmailImpl implements IStudentOnboardEmail {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void studentOnboardEmail(String email, String name, String password, String username) {
		
		logger.debug("Student onboard Email : START");
		
		EmailService sgEmailService = new SendGridEmailService();
		Properties propertyMap = PropertiesParser.getPropertyMap();
		String body = propertyMap.getProperty("studentOnboardingEmail").replace("&StudentName", name)
				.replace("&username", username).replace("&password", password);
		sgEmailService.sendHTML(email, propertyMap.getProperty("studentOnboardingSubject"), body);

		logger.debug("Student onboard Email : END");
	}

}
