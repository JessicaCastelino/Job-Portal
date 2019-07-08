package com.dal.mycareer.emailengine;

import com.dal.mycareer.propertiesparser.PropertiesParser;
import com.sendgrid.SendGrid;

public class SendGridSingleton {

	private static SendGrid instance;

	// Returns singleton instance of SendGrid
	public static SendGrid Instance() {
		if (null == instance) {
			instance = new SendGrid(PropertiesParser.getPropertyMap().get("sendgrid.api.key").toString());
		}
		return instance;
	}

}
