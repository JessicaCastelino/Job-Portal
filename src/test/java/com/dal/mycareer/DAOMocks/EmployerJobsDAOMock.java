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
        jobdetails1.setJobStatus(true);
        jobDetailsList.add(jobdetails1);

        JobDetails jobdetails2 = new JobDetails();
        jobdetails2.setId(2);
        jobdetails2.setJobTitle("Product Dev");
        jobdetails2.setHourPerWeek(38);
        jobdetails2.setRateOfPay(25);
        jobdetails2.setNoOfPosition(2);
        jobdetails2.setEmployeeId(2);
        jobdetails2.setJobStatus(false);
        jobDetailsList.add(jobdetails2);

        JobDetails jobdetails3 = new JobDetails();
        jobdetails3.setId(3);
        jobdetails3.setJobTitle("Automation Tester");
        jobdetails3.setHourPerWeek(35);
        jobdetails3.setRateOfPay(18);
        jobdetails3.setNoOfPosition(3);
        jobdetails3.setEmployeeId(3);
        jobdetails3.setJobStatus(true);
        jobDetailsList.add(jobdetails3);

        JobDetails jobdetails4 = new JobDetails();
        jobdetails4.setId(4);
        jobdetails4.setJobTitle("Technical Manager");
        jobdetails4.setHourPerWeek(40);
        jobdetails4.setRateOfPay(30);
        jobdetails4.setNoOfPosition(1);
        jobdetails4.setEmployeeId(4);
        jobdetails4.setJobStatus(false);
        jobDetailsList.add(jobdetails4);

    }
    @Override
    public List<Job> getActiveJobs(String username) 
    {
       ArrayList <Job> lstActiveJobs = new ArrayList<Job>(); 
        for (Job job : jobDetailsList)
        {
            if(job.getJobStatus() == true)
            {
                lstActiveJobs.add(job);              
            }
        }
        return lstActiveJobs;
    }

    @Override
    public JobDetails InsertJobDetails(JobDetails postedJobDetails, String currentUser) 
    {
        return null;
    }

    @Override
    public List<Job> getClosedJobs(String username) 
    {
        ArrayList <Job> lstClosedJobs = new ArrayList<Job>(); 
        for (Job job : jobDetailsList)
        {
            if(job.getJobStatus() == true)
            {
                lstClosedJobs.add(job);              
            }
        }
        return lstClosedJobs;
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
                jobDetails.setHourPerWeek(updatedJobDetails.getHourPerWeek());
                jobDetails.setRateOfPay(updatedJobDetails.getRateOfPay());
                jobDetails.setNoOfPosition(updatedJobDetails.getNoOfPosition());
            }
        }
        
        return false;
	}

}