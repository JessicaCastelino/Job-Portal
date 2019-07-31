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
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private IRoleModel roleModel = null;
	@Autowired
	private ICoopCoordinatorModel coopCordinatorModel;
	
	private IRecruiterRegistrationRequestDAO recruiterRequestDao = new RecruiterRegistrationRequestDAO();
	private IRecruiterRegistrationRequestModel recruiterRequestModel = new RecruiterRegistrationRequestModel();
	private IEmployerApprovalEmail approvalEmail = new EmployerApprovalEmail();
	private IPasswordGenerator passwordGenerator =  new PasswordGenerator();
	private IEmployerRejectionEmail rejectEmail = new EmployerRejectionEmailImpl();

	  @RequestMapping("/adminHome") 
	  public String loadAdminHome(Model model,HttpServletRequest request) throws SQLException  
	  { 
		  logger.debug("CoopCoordinatorController: loadAdminHome method: Entered");
		  model.addAttribute("reqPage", PROPERTY_MAP.get("adminHome").toString());
		  model.addAttribute("role", "admin"); roleModel = new RoleModel(); 
		  model = roleModel.getBasePage(model, request); 
		  model = recruiterRequestModel.fetchRecruiterRequests(model, request, recruiterRequestDao);
		  logger.debug("CoopCoordinatorController: loadAdminHome method: Exit");
		  return model.asMap().get("view").toString();
	  }
	 
	  @RequestMapping(value = { "/approve" }, method = RequestMethod.GET)
		public String approveRequest(@RequestParam("id") int recruiterRequestId, Model model, HttpServletRequest request) throws SQLException 
		{
		  	logger.debug("CoopCoordinatorController: approveRequest method: Entered");
			model.addAttribute("reqPage", PROPERTY_MAP.get("adminHome").toString());
			model.addAttribute("role", "admin");
			roleModel = new RoleModel();
			model = roleModel.getBasePage(model, request);
			model = recruiterRequestModel.approveRecruiterRequest(model, request, recruiterRequestId, recruiterRequestDao, approvalEmail, passwordGenerator);
			logger.debug("CoopCoordinatorController: approveRequest method: Exit");
			return model.asMap().get("view").toString();
		}
	  
	  @RequestMapping(value = { "/reject" }, method = RequestMethod.GET)
		public String rejectRequest(@RequestParam("id") int recruiterRequestId, Model model, HttpServletRequest request) throws SQLException 
		{
		  	logger.debug("CoopCoordinatorController: rejectRequest method: Entered");
			model.addAttribute("reqPage", PROPERTY_MAP.get("adminHome").toString());
			model.addAttribute("role", "admin");
			roleModel = new RoleModel();
			model = roleModel.getBasePage(model, request);
			model = recruiterRequestModel.rejectRecruiterRequest(model, request, recruiterRequestId, recruiterRequestDao, rejectEmail);
			logger.debug("CoopCoordinatorController: rejectRequest method: Entered");
			return model.asMap().get("view").toString();
		}

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
