package com.dal.mycareer.DTO;

public class Application 
{
    int applicationId;
    String applicantFName;
    String applicantLName;
    String applicationStatus;
    String completedCourses;
    String email;
    int jobId;
    String studentId;

    public int getApplicationId() 
    {
        return applicationId;
    }

    public void setApplicationId(int applicationId) 
    {
        this.applicationId = applicationId;
    }

    public String getApplicantFName() 
    {
        return applicantFName;
    }

    public void setApplicantFName(String applicantFName) 
    {
        this.applicantFName = applicantFName;
    }

    public String getApplicantLName() 
    {
        return applicantLName;
    }

    public void setApplicantLName(String applicantLName) 
    {
        this.applicantLName = applicantLName;
    }

    public String getApplicationStatus() 
    {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) 
    {
        this.applicationStatus = applicationStatus;
    }

    public String getCompletedCourses() 
    {
        return completedCourses;
    }

    public void setCompletedCourses(String completedCourses) 
    {
        this.completedCourses = completedCourses;
    }

    public int getJobId() 
    {
        return jobId;
    }

    public void setJobId(int jobId) 
    {
        this.jobId = jobId;
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
}