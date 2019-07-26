package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

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

import com.dal.mycareer.DTO.RecruiterRequest;
import com.dal.mycareer.imodel.ICoopCoordinatorModel;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class CoopCoordinatorController 
{
	static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private IRoleModel roleModel = null;
	@Autowired
	private ICoopCoordinatorModel coopCordinatorModel;

	  @RequestMapping("/adminHome") 
	  public String loadAdminHome(Model model,HttpServletRequest request) 
	  { 
		  model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("adminHome").toString());
		  model.addAttribute("role", "admin"); roleModel = new RoleModel(); 
		  model = roleModel.getBasePage(model, request); 
		  model = coopCordinatorModel.fetchRecruiterRequests(model, request); 
		  return model.asMap().get("view").toString(); 
	  }
	 
	  @RequestMapping(value = { "/approve" }, method = RequestMethod.GET)
		public String rejectRequest(@RequestParam("id") int recruiterRequestId, Model model, HttpServletRequest request) 
		{
			model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("adminHome").toString());
			model.addAttribute("role", "admin");
			roleModel = new RoleModel();
			model = roleModel.getBasePage(model, request);
			model = coopCordinatorModel.approveRecruiterRequest(model, request, recruiterRequestId);
			return model.asMap().get("view").toString();
		}
	  
	  @RequestMapping(value = { "/reject" }, method = RequestMethod.GET)
		public String approveRequest(@RequestParam("id") int recruiterRequestId, Model model, HttpServletRequest request) 
		{
			model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("adminHome").toString());
			model.addAttribute("role", "admin");
			roleModel = new RoleModel();
			model = roleModel.getBasePage(model, request);
			model = coopCordinatorModel.rejectRecruiterRequest(model, request, recruiterRequestId);
			return model.asMap().get("view").toString();
		}

	@ResponseBody
	@RequestMapping(value = "/activeRecruiters", method = RequestMethod.GET)
	public List<RecruiterRequest> showActiveRecruiter(ModelMap model) 
	{
		logger.info("Controller: Inside showActiveRecruiter method");
		return coopCordinatorModel.fetchActiveRecruiters();
	}

	@ResponseBody
	@RequestMapping(value ="/deleteRecruiter", method = RequestMethod.DELETE)
	public boolean deleteActiveEmployer(@RequestParam(name = "id") int employerId)
	{
		logger.info("Controller: Inside deleteActiveEmployer method with employerId-" + employerId);
		return coopCordinatorModel.deleteActiveRecruiter(employerId);
	}
}
