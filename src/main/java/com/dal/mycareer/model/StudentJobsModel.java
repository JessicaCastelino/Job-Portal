package com.dal.mycareer.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.imodel.IStudentJobsModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

public class StudentJobsModel implements IStudentJobsModel {
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private static List<JobDetails> jobs = new ArrayList<JobDetails>();
	private static List<AppliedJob> appliedJobs = new ArrayList<AppliedJob>();
	private static final String SESSION_NAME = "sessionName";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Student student = null;

	@Override
	public Model fetchStudentSpecificJobs(Model model, HttpServletRequest request, IStudentJobsDAO studentJobDao, IStudentDetailsDAO studentDao) throws SQLException {
		logger.debug("StudentModel: fetchJobs method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
		student = studentDao.getStudentDetails(userSessionName);
		jobs = studentJobDao.getAllJobList(student.getId(), PROPERTY_MAP.get("job_type").toString());
		appliedJobs = studentJobDao.getAppliedJobList(student.getId());
		model.addAttribute("jobs", jobs);
		model.addAttribute("appliedJobs", appliedJobs);
		}
		logger.debug("StudentModel: fetchJobs method: Exit");
		return model;
	}
	@Override
	public Model filterStudentSpecificJobList(Model model, HttpServletRequest request, String location, String jobType)
	{
		logger.debug("StudentModel: filterStudentSpecificJobList method: Entered");
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
			List<JobDetails> filteredJob=new ArrayList<JobDetails>();
			
		
		for(JobDetails job:jobs)
		{
			if(location==null || location=="" || jobType==null || jobType=="")
			{
				if(job.getLocation().equalsIgnoreCase(location)||job.getJobType().equalsIgnoreCase(jobType)) {
					filteredJob.add(job);
				}	
			}
			else
			{
				if(job.getLocation().equalsIgnoreCase(location)&&job.getJobType().equalsIgnoreCase(jobType)){
					filteredJob.add(job);
					}
			}
		}
		model.addAttribute("jobs", filteredJob);
		model.addAttribute("appliedJobs", appliedJobs);
		}
		logger.debug("StudentModel: filterStudentSpecificJobList method: Exit");
		return model;
	}
	
	
}
