package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dal.mycareer.DAO.Impl.RecruiterRegistrationRequestDAO;
import com.dal.mycareer.DAO.Interface.IRecruiterRegistrationRequestDAO;
import com.dal.mycareer.emailengine.EmployerApprovalEmail;
import com.dal.mycareer.emailengine.EmployerRejectionEmailImpl;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.imodel.IRecruiterRegistrationRequestModel;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.model.RecruiterRegistrationRequestModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class RecruiterRequestsController
{
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private static Logger logger;
	private IRecruiterRegistrationRequestDAO recruiterRequestDao;
	private IRecruiterRegistrationRequestModel recruiterRequestModel;
	private IEmployerApprovalEmail approvalEmail;
	private IPasswordGenerator passwordGenerator;
	private IEmployerRejectionEmail rejectEmail;
	private IRoleModel roleModel;

	
	public RecruiterRequestsController()
	{
		logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		recruiterRequestDao = new RecruiterRegistrationRequestDAO();
		recruiterRequestModel = new RecruiterRegistrationRequestModel();
		approvalEmail = new EmployerApprovalEmail();
		passwordGenerator =  new PasswordGenerator();
		rejectEmail = new EmployerRejectionEmailImpl();
		roleModel = new RoleModel();
	}

	@RequestMapping("/adminHome") 
	  public String loadAdminHome(Model model,HttpServletRequest request) throws SQLException  
	  { 
		  logger.debug("RecruiterRequestsController: loadAdminHome method: Entered");
		  model.addAttribute("reqPage", PROPERTY_MAP.get("adminHome").toString());
		  model.addAttribute("role", "admin"); roleModel = new RoleModel(); 
		  model = roleModel.getBasePage(model, request); 
		  model = recruiterRequestModel.fetchRecruiterRequests(model, request, recruiterRequestDao);
		  logger.debug("RecruiterRequestsController: loadAdminHome method: Exit");
		  return model.asMap().get("view").toString();
	  }
	 
	  @RequestMapping(value = { "/approve" }, method = RequestMethod.GET)
		public String approveRequest(@RequestParam("id") int recruiterRequestId, Model model, HttpServletRequest request) throws SQLException 
		{
		  	logger.debug("RecruiterRequestsController: approveRequest method: Entered");
			model.addAttribute("reqPage", PROPERTY_MAP.get("adminHome").toString());
			model.addAttribute("role", "admin");
			roleModel = new RoleModel();
			model = roleModel.getBasePage(model, request);
			model = recruiterRequestModel.approveRecruiterRequest(model, request, recruiterRequestId, recruiterRequestDao, approvalEmail, passwordGenerator);
			logger.debug("RecruiterRequestsController: approveRequest method: Exit");
			return model.asMap().get("view").toString();
		}
	  
	  @RequestMapping(value = { "/reject" }, method = RequestMethod.GET)
		public String rejectRequest(@RequestParam("id") int recruiterRequestId, Model model, HttpServletRequest request) throws SQLException 
		{
		  	logger.debug("RecruiterRequestsController: rejectRequest method: Entered");
			model.addAttribute("reqPage", PROPERTY_MAP.get("adminHome").toString());
			model.addAttribute("role", "admin");
			roleModel = new RoleModel();
			model = roleModel.getBasePage(model, request);
			model = recruiterRequestModel.rejectRecruiterRequest(model, request, recruiterRequestId, recruiterRequestDao, rejectEmail);
			logger.debug("RecruiterRequestsController: rejectRequest method: Entered");
			return model.asMap().get("view").toString();
		}
}
