package com.dal.mycareer.configlogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.dal.mycareer.propertiesparser.PropertiesParser;

public class ConfigLogicClassLoader {

	private static final String JOB_TYPE = "job_type";
	private static final String PREREQUISITE = "prerequisite";
	private static final String ALL = "all";
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();

	private static final Map<String, FetchJobs> configMap = new HashMap<String, FetchJobs>();

	public ConfigLogicClassLoader() {
		configMap.put(ALL, new FetchAllJobs());
		configMap.put(PREREQUISITE, new FetchPrerequisiteCourseJobs());
	}

	public String getJobClass() {
		FetchJobs jobclass = configMap.get(PROPERTY_MAP.getProperty(JOB_TYPE).toString());
		return jobclass.getJobProcedure();
	}

}
