package com.dal.mycareer.DAOMocks;

import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IJobsDAO;
import com.dal.mycareer.DTO.Job;

public class JobsDAOMock implements IJobsDAO 
{
    private List<Job> jobs;

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
        jobs.add(job);
    }

    @Override
    public boolean updateJobStatus(int jobRecordId, boolean jobStatus) 
    {
        boolean isUpdated = false;
        jobs.forEach(job -> 
        {
            if(job.getId() == jobRecordId)
            {
                //isUpdated = true;
                //job.setJobStatus(jobStatus);
            }
        });
        return isUpdated;
    }

}