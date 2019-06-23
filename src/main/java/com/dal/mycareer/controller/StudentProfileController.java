package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dal.mycareer.DAO.Impl.StudentProfileDAO;
import com.dal.mycareer.DAO.Interface.IStudentProfileDAO;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.imodel.IStudentProfileModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.model.StudentProfileModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class StudentProfileController {
	
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@RequestMapping(value = "/studentProfile", method = RequestMethod.GET)
	public String Login(Model model, HttpServletRequest request) {
		
		LOGGER.info("Student controller : Entered");

		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("studentProfile").toString());
		model.addAttribute("role", "student");
		
		IStudentProfileModel spModel = new StudentProfileModel();
		IStudentProfileDAO spDAO = new StudentProfileDAO();

		IRoleModel roleModel = new RoleModel();

		model = roleModel.getBasePage(model, request);
		
		if(request.getSession().getAttribute("sessionName") != null) {
			spModel.getStudentProfile(model, request.getSession().getAttribute("sessionName").toString(), spDAO);
		}
		
		LOGGER.info("Student controller : Exit");

		return model.asMap().get("view").toString();
	}

}
