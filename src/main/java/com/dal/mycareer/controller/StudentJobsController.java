package com.dal.mycareer.controller;

import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dal.mycareer.DAO.Impl.StudentApplicationDAO;
import com.dal.mycareer.DAO.Impl.StudentDetailsDAO;
import com.dal.mycareer.DAO.Impl.StudentJobsDAO;
import com.dal.mycareer.DAO.Interface.IStudentApplicationDAO;
import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;
import com.dal.mycareer.DTO.Filter;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.imodel.IStudentJobsModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.model.StudentJobsModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class StudentJobsController {
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private IRoleModel roleModel = null;
	private IStudentJobsModel studentModel = null;
	private IStudentDetailsDAO studentDetailsDao = new StudentDetailsDAO();
	private IStudentJobsDAO studentJobsDao = new StudentJobsDAO();
	IStudentApplicationDAO studentApplicationDao = new StudentApplicationDAO();
	private static final String FILTER = "filter";

	@RequestMapping(value = { "/studentHome" }, method = RequestMethod.GET)
	public String fetchAllStudentJobs(Model model, HttpServletRequest request) throws SQLException {
		logger.debug("StudentJobsController: fetchAllStudentJobs method: Entered");
		model.addAttribute("reqPage", PROPERTY_MAP.get("studentHome").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentJobsModel();
		model = studentModel.fetchStudentSpecificJobs(model, request, studentJobsDao, studentDetailsDao);
		logger.debug("StudentJobsController: fetchAllStudentJobs method: Exit");
		return model.asMap().get("view").toString();
	}

	@RequestMapping(value = { "/filter" }, method = RequestMethod.GET)
	public String filterStudentSpecificJobs(Model model, HttpServletRequest request, @ModelAttribute(FILTER) Filter filter) {
		logger.debug("StudentJobsController: filterStudentSpecificJobs method: Entered");
		model.addAttribute("reqPage", PROPERTY_MAP.get("studentHome").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentJobsModel();
		model = studentModel.filterStudentSpecificJobList(model, request, filter.getLocation(), filter.getJobType());
		logger.debug("StudentJobsController: filterStudentSpecificJobs method: Exit");
		return model.asMap().get("view").toString();
	}

}
