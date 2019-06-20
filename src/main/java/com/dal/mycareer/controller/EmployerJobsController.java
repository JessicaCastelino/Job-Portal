package com.dal.mycareer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		LOGGER.info("Redirect to activejobs.jsp");
		return "activejobs";
	}
	
	@ResponseBody
	@RequestMapping(value="/activejobs", method=RequestMethod.GET, produces="application/json")
	public List<Job> getActiveJobs() {
		LOGGER.info("Inside getActiveJobs controller");
		return employerJobs.getActiveJobs(1);
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
}
