package com.dal.mycareer.controller;

import java.util.List;

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

@Controller
public class EmployerJobsController {
	@Autowired
	IEmployerJobsModel employerJobs;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/employerdashboard")
	public String activeJobs() {
		LOGGER.info("Redirect to employerdashboard.jsp");
		return "employerdashboard";
	}
	
	@ResponseBody
	@RequestMapping(value="/activejobs", method=RequestMethod.GET, produces="application/json")
	public List<Job> getActiveJobs() {
		LOGGER.info("Inside getActiveJobs");
		return employerJobs.getActiveJobs(1);
	}
	
	@RequestMapping(value="/closedjobs", method=RequestMethod.GET, produces="application/json")
	public String getClosedJobs(ModelMap model) {
		LOGGER.info("Inside getClosedJobs");
		model.addAttribute("closedJobs", employerJobs.getClosedJobs(1));
		return "closedjobs";
	}
	
	@RequestMapping( value="/postjob", method=RequestMethod.GET)
	public String postJob() {
		
		return "postjob";
	}
	@ResponseBody
	@RequestMapping( value="/saveJob", method=RequestMethod.POST)
	public JobDetails saveJob(@RequestBody JobDetails postedjobDetails ) {
		
		return employerJobs.InsertJobDetails(postedjobDetails);
	}

	@ResponseBody
	@RequestMapping( value="/closeJob", method=RequestMethod.PUT)
	public boolean closeJob(@RequestParam(name = "id") int jobRecordId ) {
		
		return employerJobs.updateJobStatus(jobRecordId);
	}
}
