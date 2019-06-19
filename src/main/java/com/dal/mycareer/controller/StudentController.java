package com.dal.mycareer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dal.mycareer.model.Job;

@Controller
public class StudentController {
	private static List<Job> jobs = new ArrayList<Job>();
	
	@RequestMapping(value = { "/studentHome" }, method = RequestMethod.GET)
	public String loadHome(Model model) {
		
		Job j1=new Job("J01","Analyst","Deloitte","Toronto","4","4/08/2019");
		Job j2=new Job("J01","Analyst","Deloitte","Toronto","4","4/08/2019");
		Job j3=new Job("J01","Analyst","Deloitte","Toronto","4","4/08/2019");
		jobs.add(j1);
		jobs.add(j2);
		jobs.add(j3);
		model.addAttribute("Hey","HIE");
		model.addAttribute("jobs",jobs);
		return "studentView/homepage";
	}
}
