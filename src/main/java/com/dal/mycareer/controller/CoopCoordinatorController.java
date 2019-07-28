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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dal.mycareer.DAO.Impl.CoopCordinatorDAO;
import com.dal.mycareer.DAO.Interface.ICoopCordinatorDAO;
import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.emailengine.EmployerApprovalEmail;
import com.dal.mycareer.emailengine.EmployerRejectionEmailImpl;
import com.dal.mycareer.emailengine.IEmployerApprovalEmail;
import com.dal.mycareer.emailengine.IEmployerRejectionEmail;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class CoopCoordinatorController 
{
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private IRoleModel roleModel = null;
	@Autowired
	ICoopCoordinatorModel coopCordinatorModel;
	@Autowired
	ICoopCordinatorDAO coopCordinatorDAO=new CoopCordinatorDAO();
	@Autowired
	IEmployerApprovalEmail approvalEmail;
	@Autowired
	IPasswordGenerator passwordGenerator;
	@Autowired
	IEmployerRejectionEmail rejectEmail;

	  @RequestMapping("/adminHome") 
	  public String loadAdminHome(Model model,HttpServletRequest request)  
	  { 
		  logger.debug("CoopCoordinatorController: loadAdminHome method: Entered");
		  model.addAttribute("reqPage", PROPERTY_MAP.get("adminHome").toString());
		  model.addAttribute("role", "admin"); roleModel = new RoleModel(); 
		  model = roleModel.getBasePage(model, request); 
		  try
		{
			logger.debug("Before");
			model = coopCordinatorModel.fetchRecruiterRequests(model, request, coopCordinatorDAO);
			logger.debug("After");
		} catch (SQLException e)
		{
			logger.debug("Redirecting");
			return "exception";
		}
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
			model = coopCordinatorModel.approveRecruiterRequest(model, request, recruiterRequestId, coopCordinatorDAO, approvalEmail, passwordGenerator);
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
			model = coopCordinatorModel.rejectRecruiterRequest(model, request, recruiterRequestId, coopCordinatorDAO, rejectEmail);
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
	
	@ExceptionHandler(SQLException.class)
	  public ModelAndView myError(Exception exception) {
	    ModelAndView mv = new ModelAndView();
	    System.out.println(exception.getMessage());
	    System.out.println(" ************** "+exception.getLocalizedMessage());
	    mv.addObject("exception", exception);
	    mv.setViewName("exception");
	    logger.debug("Redirecting to error page");
	    return mv;
	  }
	
}
