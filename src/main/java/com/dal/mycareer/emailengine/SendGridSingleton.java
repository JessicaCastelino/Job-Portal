package com.dal.mycareer.emailengine;

import java.lang.invoke.MethodHandles;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.propertiesparser.PropertiesParser;
import com.sendgrid.SendGrid;

public class SendGridSingleton {

	private static SendGrid instance;
	static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	// Returns singleton instance of SendGrid
	public static SendGrid Instance() {

		logger.debug("SendGrid instance : START");

		if (null == instance) {
			Properties propertyMap = PropertiesParser.getPropertyMap();
			instance = new SendGrid(propertyMap.get("sendgrid.api.key").toString());
		}
		logger.debug("SendGrid instance : END");
		return instance;
	}

}
