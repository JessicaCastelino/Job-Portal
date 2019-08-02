package com.dal.mycareer.configlogic;

public class FetchPrerequsiteCourseJobs extends FetchJobs {

	private static final String CALL_GET_PREQUSITE_JOBS = "{call getCompletePrerequisiteJobList(?)}";

	public String getJobProcedure() {
		return CALL_GET_PREQUSITE_JOBS;
	}

}
