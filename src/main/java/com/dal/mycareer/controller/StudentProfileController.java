package com.dal.mycareer.controller;

import java.util.Properties;

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
	
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private static final String VIEW = "view";
	private static final String SESSION_NAME = "sessionName";
	private static final String STUDENT = "student";
	private static final String ROLE = "role";
	private static final String STUDENT_PROFILE = "studentProfile";
	private static final String REQ_PAGE = "reqPage";
	
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/studentProfile", method = RequestMethod.GET)
	public String Login(Model model, HttpServletRequest request) {
		
		logger.info("Student controller : START");

		model.addAttribute(REQ_PAGE, PROPERTY_MAP.get(STUDENT_PROFILE).toString());
		model.addAttribute(ROLE, STUDENT);
		
		IStudentProfileModel spModel = new StudentProfileModel();
		IStudentProfileDAO spDAO = new StudentProfileDAO();

		IRoleModel roleModel = new RoleModel();

		model = roleModel.getBasePage(model, request);
		
		if(request.getSession().getAttribute(SESSION_NAME) != null) {
			spModel.getStudentProfile(model, request.getSession().getAttribute(SESSION_NAME).toString(), spDAO);
			
			logger.debug("Student profile fetched");
		}
		
		logger.info("Student controller : END");

		return model.asMap().get(VIEW).toString();
	}

}
