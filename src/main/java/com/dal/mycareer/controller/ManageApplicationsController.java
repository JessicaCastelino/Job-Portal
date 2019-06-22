package com.dal.mycareer.controller;

import com.dal.mycareer.imodel.IManageApplicationsModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageApplicationsController {
	@Autowired
	IManageApplicationsModel applicationManager;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/applications")
	public String viewApplicants(ModelMap model, @RequestParam(name = "jobId")int id) {
        LOGGER.info("Redirect to applications.jsp");
        model.addAttribute("applicants", applicationManager.getApplications(id));
		return "applications";
	}
}