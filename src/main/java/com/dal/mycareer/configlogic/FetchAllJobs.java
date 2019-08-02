package com.dal.mycareer.configlogic;

public class FetchAllJobs extends FetchJobs{

	private static final String CALL_GET_ALL_JOB_LIST = "{call getAllJobList(?)}";

	public String getJobProcedure() {
		return CALL_GET_ALL_JOB_LIST;
	}

}
