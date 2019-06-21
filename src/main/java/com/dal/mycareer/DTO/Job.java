package com.dal.mycareer.DTO;

import java.util.Date;
import java.util.List;
	public class Job {
	public int id;
	public String jobId;
	public String jobTitle;
	public String location;
	public String jobType;
	public Date applicationDeadline;
	public String organization;
	public List<Integer> selectedCourseIds;
}
