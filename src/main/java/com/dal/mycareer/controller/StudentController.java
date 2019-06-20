package com.dal.mycareer.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Impl.FileDAO;
import com.dal.mycareer.DAO.Impl.StudentDAO;
import com.dal.mycareer.DAO.Interface.IStudentDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;

@Controller
public class StudentController {
	private static List<Job> jobs = new ArrayList<Job>();
	private static List<AppliedJob> appliedJobs = new ArrayList<AppliedJob>();
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	IStudentDAO dao=null;
	
	@RequestMapping(value = { "/studentHome" }, method = RequestMethod.GET)
	public String loadHome(Model model) {
		dao=new StudentDAO();
		jobs=dao.getAllJobList();
		appliedJobs=dao.getAppliedJobList();
		model.addAttribute("jobs",jobs);
		model.addAttribute("appliedJobs",appliedJobs);
		return "studentView/homepage";
	}
	
	@RequestMapping(value = { "/viewJob" }, method = RequestMethod.GET)
	public String viewJob(@RequestParam("id") String jobId, Model model) {
		System.out.println("JOB ID:"+jobId);
		for (Job job : jobs) {
			if(job.getId().equalsIgnoreCase(jobId))
			   model.addAttribute("job",job);
			}
		return "studentView/viewJob";
	}
	
	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		InputStream inputStream = null;
		
		FileDAO dao=new FileDAO();
		if(file!=null)
		{
		 System.out.println(String.format("File name %s", file.getOriginalFilename()));
		 try {
			 inputStream = file.getInputStream();
			 int i=dao.uploadFile(inputStream);
			 if(i==1)
			 {
				 System.out.println("File Uploaded..");
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		   
		return "homepage";
	}
	@RequestMapping(value = { "/applyJob" }, method = RequestMethod.GET)
	public String loadHome() {
		return "studentView/applyJob";
	}
		
		
}
