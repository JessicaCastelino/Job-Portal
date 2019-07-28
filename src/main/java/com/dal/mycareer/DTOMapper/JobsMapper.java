package com.dal.mycareer.DTOMapper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.dal.mycareer.DTO.Job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobsMapper implements IDTOMapper 
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int[] mapObjectToStatement(Object jobDetails, CallableStatement callStatement,
            Map<String, Object> additionalParam) 
    {
        // This method will be implemented, if bulk insert of Job feature is added in future
        return null;
    }

    @Override
    public Object mapStatementtoObject(ResultSet resultSet, Object dtoObject) 
    {
        List<Job> jobs = (List<Job>) dtoObject;
        Job job;
        try
        {
            while(resultSet.next()) 
            {
                job = new Job();
                job.setId(resultSet.getInt("id"));
                job.setJobTitle(resultSet.getString("jobTitle"));
                job.setJobType(resultSet.getString("jobType"));
                job.setLocation(resultSet.getString("location"));
                job.setOrganization(resultSet.getString("organization"));
                job.setApplicationDeadline(resultSet.getDate("applicationDeadline"));
                job.setRequiredCourses(resultSet.getString("requiredCourses"));
                jobs.add(job);
            }
        }
        catch(Exception ex)
        {
            logger.error("Error Occurred in mapStatementtoObject for Jobs :" + ex.getMessage());
        }
        return jobs;
    }
    
}