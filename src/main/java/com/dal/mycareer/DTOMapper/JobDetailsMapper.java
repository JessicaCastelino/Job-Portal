package com.dal.mycareer.DTOMapper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dal.mycareer.DTO.JobDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobDetailsMapper implements IDTOMapper 
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JobDetails mapStatementtoObject(ResultSet result, Object jobDetails) 
    {
        List<Integer> lstCourseList = new ArrayList<Integer>();
        JobDetails postJobDetails;
        postJobDetails = (JobDetails) jobDetails;
        try 
        {
            while (result.next()) 
            {
                postJobDetails.setId(result.getInt("id"));
                postJobDetails.setJobTitle(result.getString("jobTitle"));
                postJobDetails.setLocation(result.getString("location"));
                postJobDetails.setNoOfPosition(result.getInt("openPosition"));
                postJobDetails.setJobType(result.getString("jobType"));
                postJobDetails.setRateOfPay(result.getInt("rateofPay"));
                postJobDetails.setHourPerWeek(result.getInt("hoursPerWeek"));
                postJobDetails.setApplicationDeadline(result.getDate("applicationDeadline"));
                postJobDetails.setJobDescription(result.getString("jobDescription"));
                String preRequisiteCourses = result.getString("prerequisitecourses");
                String[] courseArray = preRequisiteCourses.trim().split(",");
                for (String course : courseArray) 
                {
                    lstCourseList.add(Integer.parseInt(course.trim()));
                }
                postJobDetails.setSelectedCourseIds(lstCourseList);
            }
            return postJobDetails;
        } 
        catch (Exception ex) 
        {
            logger.error("Error Occurred in mapStatementtoObject for JobDetails :" + ex.getMessage());
        }
        return null;
    }

    @Override
    public int[] mapObjectToStatement(Object postedJobDetails, CallableStatement callStatement, Map<String, Object> additionalParam) {
        try 
        {
            JobDetails postJobDetails;
            postJobDetails = (JobDetails) postedJobDetails;
            callStatement.setString("jobTitle", postJobDetails.getJobTitle());
            callStatement.setString("jobLocation", postJobDetails.getLocation());
            callStatement.setString("jobType", postJobDetails.getJobType());
            callStatement.setString("noOfPosition", Integer.toString(postJobDetails.noOfPosition));
            callStatement.setString("rateOfPay", Integer.toString(postJobDetails.rateOfPay));
            callStatement.setString("hourPerWeek", Integer.toString(postJobDetails.hourPerWeek));
            callStatement.setString("jobDescription", postJobDetails.jobDescription);
            callStatement.setString("emailId", (String) additionalParam.get("emailId"));
            callStatement.setDate("applicationDeadline", postJobDetails.getApplicationDeadline());
            callStatement.registerOutParameter(10, java.sql.Types.INTEGER);
            int[] outparamIndex = new int[] { 10 };
            return outparamIndex;
        } 
        catch (Exception ex) 
        {
            logger.error("Error Occurred in mapObjectToStatement for JobDetails :" + ex.getMessage());
        }
        return null;

    }

}