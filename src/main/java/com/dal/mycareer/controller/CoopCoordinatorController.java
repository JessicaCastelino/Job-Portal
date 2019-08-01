package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dal.mycareer.DAO.Impl.RecruiterRegistrationRequestDAO;
import com.dal.mycareer.DAO.Interface.IRecruiterRegistrationRequestDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.EmployerApprovalEmail;
import com.dal.mycareer.emailengine.EmployerRejectionEmailImpl;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;
import com.dal.mycareer.imodel.IRecruiterRegistrationRequestModel;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.model.RecruiterRegistrationRequestModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class CoopCoordinatorController 
{
	private static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private ICoopCoordinatorModel coopCordinatorModel;  

	@ResponseBody
	@RequestMapping(value = "/activeRecruiters", method = RequestMethod.GET)
	public List<RecruiterRequest> showActiveRecruiter(ModelMap model) 
	{
		logger.debug("Controller: Inside showActiveRecruiter method");
		return coopCordinatorModel.fetchActiveRecruiters();
	}

	@ResponseBody
	@RequestMapping(value ="/deleteRecruiter", method = RequestMethod.DELETE)
	public boolean deleteActiveEmployer(@RequestParam(name = "id") int employerId)
	{
		logger.debug("Controller: Inside deleteActiveEmployer method with employerId-" + employerId);
		return coopCordinatorModel.deleteActiveRecruiter(employerId);
	}
}
