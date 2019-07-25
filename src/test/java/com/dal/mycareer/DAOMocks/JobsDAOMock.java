package com.dal.mycareer.DAOMocks;

import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.DTO.Job;

public class JobsDAOMock implements IJobsDAO 
{
    private List<Job> jobs;
    private boolean isUpdated;
    
    JobsDAOMock()
    {
        jobs = new ArrayList<>();
        Job job = new Job();
        job.setId(1);
        job.setJobTitle("Associate Consultant");
        job.setLocation("Halifax");
        job.setJobType("Full-time");
        job.setOrganization("IBM");
        job.setEmployeeId(1);
        job.setJobStatus(false);
        jobs.add(job);
        job = new Job();
        job.setId(2);
        job.setJobTitle("Associate Consultant");
        job.setLocation("Halifax");
        job.setJobType("Full-time");
        job.setOrganization("IBM");
        job.setEmployeeId(2);
        job.setJobStatus(true);
        jobs.add(job);
    }

    @Override
    public boolean updateJobStatus(int jobRecordId, boolean jobStatus) 
    {
        this.isUpdated = false;
        jobs.forEach(job -> 
        {
            if(job.getId() == jobRecordId && job.getJobStatus() != jobStatus)
            {
                this.isUpdated = true;
                job.setJobStatus(jobStatus);
            }
        });
        return isUpdated;
    }

}