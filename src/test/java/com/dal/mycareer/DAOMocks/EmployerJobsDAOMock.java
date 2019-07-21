package com.dal.mycareer.DAOMocks;

import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IEmployerJobsDAO;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;

public class EmployerJobsDAOMock implements IEmployerJobsDAO 
{
    ArrayList <JobDetails> jobDetailsList;
    public EmployerJobsDAOMock()
    {
        jobDetailsList = new ArrayList<JobDetails>();
        JobDetails jobdetails1 = new JobDetails();
        jobdetails1.setId(1);
        jobdetails1.setJobTitle("Associate Consultant");
        jobdetails1.setHourPerWeek(40);
        jobdetails1.setRateOfPay(15);
        jobdetails1.setNoOfPosition(10);
        jobdetails1.setEmployeeId(1);
        jobDetailsList.add(jobdetails1);

        JobDetails jobdetails2 = new JobDetails();
        jobdetails2.setId(2);
        jobdetails2.setJobTitle("Product Dev");
        jobdetails2.setHourPerWeek(38);
        jobdetails2.setRateOfPay(25);
        jobdetails2.setNoOfPosition(2);
        jobdetails2.setEmployeeId(2);
        jobDetailsList.add(jobdetails2);

    }
    @Override
    public List<Job> getActiveJobs(String username) 
    {
        
        return null;
    }

    @Override
    public JobDetails InsertJobDetails(JobDetails postedJobDetails, String currentUser) 
    {
        return null;
    }

    @Override
    public List<Job> getClosedJobs(String username) 
    {
        return null;
    }

    @Override
    public JobDetails viewPostedJobDetails(int jobId) 
    {
        
        for (JobDetails jobDetails : jobDetailsList)
        {
            if(jobDetails.getId() == jobId)
            {
                return (jobDetails);
            }
        }    
        return null;
    }

	@Override
    public boolean updatejobDetails(JobDetails updatedJobDetails) 
    {
        for(JobDetails jobDetails : jobDetailsList)
        {
            if(jobDetails.getId() == updatedJobDetails.getId())
            {
                jobDetails.setJobTitle(updatedJobDetails.getJobTitle());
            }
        }
        
        return false;
	}

}