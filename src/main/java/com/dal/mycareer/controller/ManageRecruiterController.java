package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManageRecruiterController
{
	@Autowired
	ICoopCoordinatorModel coopCordinatorModel;
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@ResponseBody
	@RequestMapping(value ="/activeRecruiters", method = RequestMethod.GET)
	 public List<RecruiterRequest> showActiveRecruiter(ModelMap model)
	 {
		  
		  LOGGER.info("Redirect to adminHome.jsp");		
		  return coopCordinatorModel.fetchActiveRecruiters();		  
	 }
}