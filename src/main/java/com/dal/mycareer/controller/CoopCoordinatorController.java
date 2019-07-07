package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dal.mycareer.imodel.ICoopCoordinatorModel;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.model.CoopCoordinatorModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.model.StudentModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class CoopCoordinatorController {
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	IRoleModel roleModel = null;
	ICoopCoordinatorModel coopCordinatorModel=null;
	private static final String FILTER = "filter";

	
	
	  @RequestMapping("/adminHome") 
	  public String loadAdminHome(Model model,HttpServletRequest request) { 
		  model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("adminHome").toString());
		  model.addAttribute("role", "admin"); roleModel = new RoleModel(); 
		  model = roleModel.getBasePage(model, request); 
		  coopCordinatorModel = new CoopCoordinatorModel(); 
		  model = coopCordinatorModel.fetchRecruiterRequests(model, request); 
		  return model.asMap().get("view").toString(); 
	      }
	 
	  @RequestMapping(value = { "/approve" }, method = RequestMethod.GET)
		public String rejectRequest(@RequestParam("id") int recruiterRequestId,@RequestParam("email") String email, Model model, HttpServletRequest request) {
			model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("adminHome").toString());
			model.addAttribute("role", "admin");
			roleModel = new RoleModel();
			model = roleModel.getBasePage(model, request);
			coopCordinatorModel = new CoopCoordinatorModel();
			model = coopCordinatorModel.approveRecruiterRequest(model, request, recruiterRequestId, email);
			return model.asMap().get("view").toString();
		}
	  
	  @RequestMapping(value = { "/reject" }, method = RequestMethod.GET)
		public String approveRequest(@RequestParam("id") int recruiterRequestId, Model model, HttpServletRequest request) {
			model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("adminHome").toString());
			model.addAttribute("role", "admin");
			roleModel = new RoleModel();
			model = roleModel.getBasePage(model, request);
			coopCordinatorModel = new CoopCoordinatorModel();
			model = coopCordinatorModel.rejectRecruiterRequest(model, request, recruiterRequestId);
			return model.asMap().get("view").toString();
		}

}
