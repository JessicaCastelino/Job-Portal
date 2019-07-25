package com.dal.mycareer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.JobDetails;
import com.dal.mycareer.imodel.IEmployerJobsModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class EmployerJobsController
 {
	@Autowired
	IEmployerJobsModel employerJobs;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/employerdashboard")
	public String activeJobs(ModelMap model)
	{
		model.addAttribute("jobTypes", PropertiesParser.getPropertyMap().get("jobTypes").toString().split(","));
		logger.info("Redirect to employerdashboard.jsp");
		return "employerHome";
	}
	
	@ResponseBody
	@RequestMapping(value="/activejobs", method=RequestMethod.GET, produces="application/json")
	public List<Job> getActiveJobs(HttpServletRequest request) 
	{
		String currentUser = (String) request.getSession().getAttribute("sessionName");
		logger.info("Controller: Inside getActiveJobs for user-" + currentUser);
		return employerJobs.getActiveJobs(currentUser);
	}
	
	@RequestMapping(value="/closedjobs", method=RequestMethod.GET, produces="application/json")
	public String getClosedJobs(ModelMap model, HttpServletRequest request) 
	{
		String currentUser = (String) request.getSession().getAttribute("sessionName");
		logger.info("Controller: Inside getClosedJobs for user-" + currentUser);
		model.addAttribute("closedJobs", employerJobs.getClosedJobs(currentUser));
		return "closedjobs";
	}
	
	@RequestMapping( value="/postjob", method=RequestMethod.GET)
	public String postJob() 
	{
		logger.info("Controller: Inside postJob method");
		return "postjob";
	}
	
	@ResponseBody
	@RequestMapping( value="/saveJob", method=RequestMethod.POST)
	public JobDetails saveJob(@RequestBody JobDetails postedjobDetails, HttpServletRequest request ) 
	{
		String currentUser = (String) request.getSession().getAttribute("sessionName");
		logger.info("Controller: Inside saveJob method for user-" + currentUser);
		return employerJobs.InsertJobDetails(postedjobDetails, currentUser);
	}

	@RequestMapping("/viewPostedJob")
	public String viewPostedJob(ModelMap model, @RequestParam(name ="jobId") int jobId) 
	{
		logger.info("Controller: Inside viewPostedJob method for jobId-" + jobId);
		model.addAttribute("jobDetails", employerJobs.viewPostedJobDetails(jobId));
		logger.info("Controller: Redirect to viewPostedJob.jsp");
		return "viewpostedjobdetails";
	}
	@RequestMapping("/editPostedJob")
	public String editPostedJob(ModelMap model, @RequestParam(name ="jobId") int jobId) 
	{		
		logger.info("Controller: Inside editPostedJob method for jobId-" + jobId);
		model.addAttribute("jobDetails", employerJobs.viewPostedJobDetails(jobId));
		model.addAttribute("jobTypes", PropertiesParser.getPropertyMap().get("jobTypes").toString().split(","));		
		logger.info("Controller: Redirect to editPostedJob.jsp");
		return "editpostedjobdetails";
	}
	@ResponseBody
	@RequestMapping(value ="/updateJobDetails", method=RequestMethod.PUT)
	public boolean updateJobDetails(@RequestBody JobDetails updatedJobDetails)
	{
		logger.info("Controller: Inside updateJobDetails method for jobId-" + updatedJobDetails.getId());
		return employerJobs.updateJobDetails(updatedJobDetails) ;
	}
}
