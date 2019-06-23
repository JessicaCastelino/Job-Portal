package com.dal.mycareer.DTO;

public class JobDetails extends Job 
{
	public Integer noOfPosition;
	public Integer rateOfPay;
	public Integer hourPerWeek;
	public String jobDescription;

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
