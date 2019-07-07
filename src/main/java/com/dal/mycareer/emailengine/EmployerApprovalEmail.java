package com.dal.mycareer.emailengine;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class EmployerApprovalEmail implements IEmployerApprovalEmail {

	@Override
	public void employerApprovalEmail(String email, String name, String company, String password, String username) {
		SendGridEmailService sgEmailService = new SendGridEmailService();
		String body = PropertiesParser.getPropertyMap().getProperty("employerApprovalEmail")
				.replace("&EmployerName", name).replace("&username", email).replace("&password", password)
				.replace("&organization", company);
		sgEmailService.sendHTML(email, PropertiesParser.getPropertyMap().getProperty("employerApprovalSubject"), body);
	}

}
