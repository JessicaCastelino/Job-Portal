package com.dal.mycareer.DTO;

import java.util.Date;
import java.util.List;

public class Job
{
int id;
String jobTitle;
String location;
String jobType;
Date applicationDeadline;
String organization;
List<Integer> selectedCourseIds;
int employeeId; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public Date getApplicationDeadline() {
		return applicationDeadline;
	}

	public void setApplicationDeadline(Date applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public List<Integer> getSelectedCourseIds() {
		return selectedCourseIds;
	}

	public void setSelectedCourseIds(List<Integer> selectedCourseIds) {
		this.selectedCourseIds = selectedCourseIds;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
