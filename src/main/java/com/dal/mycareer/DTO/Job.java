package com.dal.mycareer.DTO;

import java.sql.Date;
import java.util.List;

public class Job 
{
	private int id;
	private String jobTitle;
	private String location;
	private String jobType;
	private Date applicationDeadline;
	private String organization;
	private String requiredCourses;
	private List<Integer> selectedCourseIds;
	private int employeeId;
  	private boolean jobStatus;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getJobTitle() 
	{
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) 
	{
		this.jobTitle = jobTitle;
	}

	public String getLocation() 
	{
		return location;
	}

	public void setLocation(String location) 
	{
		this.location = location;
	}

	public String getJobType() 
	{
		return jobType;
	}

	public void setJobType(String jobType) 
	{
		this.jobType = jobType;
	}

	public Date getApplicationDeadline() 
	{
		return applicationDeadline;
	}

	public void setApplicationDeadline(Date applicationDeadline) 
	{
		this.applicationDeadline = applicationDeadline;
	}

	public String getOrganization() 
	{
		return organization;
	}

	public void setOrganization(String organization) 
	{
		this.organization = organization;
	}

	public List<Integer> getSelectedCourseIds() 
	{
		return selectedCourseIds;
	}

	public void setSelectedCourseIds(List<Integer> selectedCourseIds) 
	{
		this.selectedCourseIds = selectedCourseIds;
	}

	public int getEmployeeId() 
	{
		return employeeId;
	}

	public void setEmployeeId(int employeeId) 
	{
		this.employeeId = employeeId;
	}

	public String getRequiredCourses() 
	{
		return requiredCourses;
	}

	public void setRequiredCourses(String requiredCourses) 
	{
		this.requiredCourses = requiredCourses;
	}

	public boolean getJobStatus() 
	{
		return jobStatus;
	}

	public void setJobStatus(boolean jobStatus) 
	{
		this.jobStatus = jobStatus;
	}
}
