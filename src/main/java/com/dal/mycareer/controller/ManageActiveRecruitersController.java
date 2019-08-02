package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.imodel.IManageActiveRecruitersModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManageActiveRecruitersController
{
    private static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private IManageActiveRecruitersModel manageActiveRecruitersModel;
    

    @ResponseBody
	@RequestMapping(value = "/activeRecruiters", method = RequestMethod.GET)
	public List<RecruiterRequest> showActiveRecruiter(ModelMap model) throws SQLException
	{
		logger.debug("Controller: Inside showActiveRecruiter method");
		return manageActiveRecruitersModel.fetchActiveRecruiters();
	}

	@ResponseBody
	@RequestMapping(value ="/deleteRecruiter", method = RequestMethod.DELETE)
	public boolean deleteActiveEmployer(@RequestParam(name = "id") int employerId) throws SQLException
	{
		logger.debug("Controller: Inside deleteActiveEmployer method with employerId-" + employerId);
		return manageActiveRecruitersModel.deleteActiveRecruiter(employerId);
	}
}