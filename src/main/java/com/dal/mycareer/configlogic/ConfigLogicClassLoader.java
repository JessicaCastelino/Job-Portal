package com.dal.mycareer.configlogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public final class ConfigLogicClassLoader {

	private static final String PREREQUISITE = "prerequisite";
	private static final String ALL = "all";
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();

	Map<String, FetchJobs> configMap = new HashMap<String, FetchJobs>();

	public static String getJobClass() {
		return PROPERTY_MAP.getProperty("job_type").toString();
	}

	public ConfigLogicClassLoader() {
		configMap.put(ALL, new FetchAllJobs());
		configMap.put(PREREQUISITE, new FetchPrerequsiteCourseJobs());
	}

}
