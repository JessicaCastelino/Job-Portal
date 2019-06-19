package com.dal.mycareer.controller;

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

import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.Job;

@Controller
public class StudentController {
	private static List<Job> jobs = new ArrayList<Job>();
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	
	@RequestMapping(value = { "/studentHome" }, method = RequestMethod.GET)
	public String loadHome(Model model) {
		Connection con=DatabaseConnection.getConnection();
		model.addAttribute("jobs",jobs);
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
		
		
}
