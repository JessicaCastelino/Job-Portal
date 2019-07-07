package com.dal.mycareer.emailengine;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class EmployerRejectionEmailImpl implements IEmployerRejectionEmail {

	@Override
	public void employerRejectionEmail(String email, String name, String company) {
		SendGridEmailService sgEmailService = new SendGridEmailService();
		String emailBody = PropertiesParser.getPropertyMap().getProperty("employerRejectionEmail")
				.replace("&EmployerName", name);
		sgEmailService.sendHTML(email, PropertiesParser.getPropertyMap().getProperty("employerRejectionSubject"),
				emailBody);
	}

}
