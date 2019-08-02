package com.dal.mycareer.DTO;

import java.sql.Date;
import java.util.List;

public class JobDetails extends Job 
{
	public Integer noOfPosition;
	public Integer rateOfPay;
	public Integer hourPerWeek;
	public String jobDescription;

	
	public JobDetails() 
	{
		super();
	}

	

	public JobDetails(Integer noOfPosition, Integer rateOfPay, Integer hourPerWeek, String jobDescription, 
			int id, String jobTitle, String location, String jobType, Date applicationDeadline, String organization,
			String requiredCourses, List<Integer> selectedCourseIds, int employeeId, boolean jobStatus) {
		super(id, jobTitle, location, jobType, applicationDeadline, organization,
				requiredCourses, selectedCourseIds, employeeId, jobStatus);
		this.noOfPosition = noOfPosition;
		this.rateOfPay = rateOfPay;
		this.hourPerWeek = hourPerWeek;
		this.jobDescription = jobDescription;
	}



	public Integer getNoOfPosition() 
	{
		return noOfPosition;
	}

	public void setNoOfPosition(Integer noOfPosition) 
	{
		this.noOfPosition = noOfPosition;
	}

	public Integer getRateOfPay() 
	{
		return rateOfPay;
	}

	public void setRateOfPay(Integer rateOfPay) 
	{
		this.rateOfPay = rateOfPay;
	}

	public Integer getHourPerWeek() 
	{
		return hourPerWeek;
	}

	public void setHourPerWeek(Integer hourPerWeek) 
	{
		this.hourPerWeek = hourPerWeek;
	}

	public String getJobDescription() 
	{
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) 
	{
		this.jobDescription = jobDescription;
	}
}
