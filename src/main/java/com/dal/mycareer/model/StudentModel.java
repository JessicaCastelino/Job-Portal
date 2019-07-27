package com.dal.mycareer.model;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Impl.StudentDAO;
import com.dal.mycareer.DAO.Interface.IStudentDAO;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.imodel.IStudentModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

public class StudentModel implements IStudentModel {
	private static List<JobDetails> jobs = new ArrayList<JobDetails>();
	private static List<AppliedJob> appliedJobs = new ArrayList<AppliedJob>();
	private static final String SESSION_NAME = "sessionName";
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	Student student = null;

	@Override
	public Model fetchJobs(Model model, HttpServletRequest request, IStudentDAO dao) {
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
		student = dao.getStudentDetails(userSessionName);
		jobs = dao.getAllJobList(student.getId());
		appliedJobs = dao.getAppliedJobList(student.getId());
		model.addAttribute("jobs", jobs);
		model.addAttribute("appliedJobs", appliedJobs);
		}
		
		return model;
	}

	@Override
	public Model viewJobs(Model model, int jobId, HttpServletRequest request, IStudentDAO dao) {
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
			for (Job job : jobs) {
				
				if (job.getId() == jobId)
				{
					model.addAttribute("job", job);
				}
			}
		}
		return model;
	}

	@Override
	public Model applyJob(Model model, MultipartFile file, HttpServletRequest request, int jobId, IStudentDAO dao) {
		InputStream inputStream = null;
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
		student = dao.getStudentDetails(userSessionName);
		if (file != null) {
			try {
				inputStream = file.getInputStream();
				int i = dao.applyForJob(inputStream,student.getId(),jobId);
				if (i == 1) {
					//System.out.println("File Uploaded..");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}

		return model;
	}

	@Override
	public Model withdrawApplication(Model model, int jobId, HttpServletRequest request, IStudentDAO dao) {
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
		student = dao.getStudentDetails(userSessionName);
		int i = dao.withdrawApplication(jobId);
		appliedJobs = dao.getAppliedJobList(student.getId());
		model.addAttribute("jobs", jobs);
		model.addAttribute("appliedJobs", appliedJobs);
		}
		return model;
	}

	@Override
	public Model filterJobs(Model model, HttpServletRequest request, String location, String jobType, IStudentDAO dao) {
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
			List<JobDetails> filteredJob=new ArrayList<JobDetails>();
			
		
		for(JobDetails job:jobs)
		{
			if(location==null || location=="" || jobType==null || jobType=="")
			{
				if(job.getLocation().equalsIgnoreCase(location)||job.getJobType().equalsIgnoreCase(jobType))
					filteredJob.add(job);
			}
			else
			{
				if(job.getLocation().equalsIgnoreCase(location)&&job.getJobType().equalsIgnoreCase(jobType))
					filteredJob.add(job);
			}
		}
		model.addAttribute("jobs", filteredJob);
		model.addAttribute("appliedJobs", appliedJobs);
		}
		return model;
	}

	@Override
	public Model jobApplicationExists(Model model, HttpServletRequest request, int jobId, IStudentDAO dao) {
		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if(userSessionName!="" && userSessionName!=null)
		{
			student = dao.getStudentDetails(userSessionName);
			int i=dao.alreadyApplied(student.getId(),jobId);
			if(i==1)
			{
				model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("alreadyApplied").toString());
			}
		}
		return model;
	}
	
	
}
