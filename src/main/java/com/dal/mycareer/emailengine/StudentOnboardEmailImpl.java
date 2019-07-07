package com.dal.mycareer.emailengine;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class StudentOnboardEmailImpl implements IStudentOnboardEmail {

	@Override
	public void studentOnboardEmail(String email, String name, String password, String username) {
		EmailService sgEmailService = new SendGridEmailService();
		String body = PropertiesParser.getPropertyMap().getProperty("studentOnboardingEmail")
				.replace("&StudentName", name).replace("&username", username).replace("&password", password);
		sgEmailService.sendHTML(email, PropertiesParser.getPropertyMap().getProperty("studentOnboardingSubject"), body);

	}

}
