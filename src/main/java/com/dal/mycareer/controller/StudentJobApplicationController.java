package com.dal.mycareer.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Impl.StudentApplicationDAO;
import com.dal.mycareer.DAO.Impl.StudentDetailsDAO;
import com.dal.mycareer.DAO.Impl.StudentJobsDAO;
import com.dal.mycareer.DAO.Interface.IStudentApplicationDAO;
import com.dal.mycareer.DAO.Interface.IStudentDetailsDAO;
import com.dal.mycareer.DAO.Interface.IStudentJobsDAO;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.imodel.IStudentApplicationModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.model.StudentApplicationModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class StudentJobApplicationController
{
	private static final Properties PROPERTY_MAP = PropertiesParser.getPropertyMap();
	private final Logger logger;
	private IRoleModel roleModel;
	private IStudentApplicationModel studentApplicationModel;
	private IStudentDetailsDAO studentDetailsDao;
	private IStudentJobsDAO studentJobsDao;
	IStudentApplicationDAO studentApplicationDao;
	
	
	public StudentJobApplicationController()
	{
		logger = LoggerFactory.getLogger(this.getClass());
		studentApplicationDao = new StudentApplicationDAO();
		studentDetailsDao = new StudentDetailsDAO();
		studentJobsDao = new StudentJobsDAO();
		roleModel = new RoleModel();
		studentApplicationModel = new StudentApplicationModel();
	}

	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public String submitApplication(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request,@RequestParam int jobId) throws SQLException, IOException {
		logger.debug("StudentJobApplicationController: submitApplication method: Entered");
		model.addAttribute("reqPage", PROPERTY_MAP.get("submitted").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentApplicationModel = new StudentApplicationModel();
		model = studentApplicationModel.submitJobApplication(model, file, request,jobId, studentDetailsDao, studentApplicationDao, studentJobsDao);
		logger.debug("StudentJobApplicationController: submitApplication method: Exit");
		return model.asMap().get("view").toString();
	}

	@RequestMapping(value = { "/applyJob" }, method = RequestMethod.GET)
	public String checkApplicationExists(Model model, HttpServletRequest request, @RequestParam int jobId) throws SQLException {
		logger.debug("StudentJobApplicationController: checkApplicationExists method: Entered");
		model.addAttribute("reqPage", PROPERTY_MAP.get("applyJob").toString());
		model.addAttribute("role", "student");
		studentApplicationModel = new StudentApplicationModel();
		model = studentApplicationModel.checkApplicationExists(model, request,jobId, studentDetailsDao, studentApplicationDao);
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		model.addAttribute("jobId",jobId);
		logger.debug("StudentJobApplicationController: checkApplicationExists method: Exit");
		return model.asMap().get("view").toString();
	}
	@RequestMapping(value = { "/withdraw" }, method = RequestMethod.GET)
	public String withdrawApplication(@RequestParam("id") int jobId, Model model, HttpServletRequest request) throws SQLException {
		logger.debug("StudentJobApplicationController: withdrawApplication method: Entered");
		model.addAttribute("reqPage", PROPERTY_MAP.get("studentHome").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentApplicationModel = new StudentApplicationModel();
		model = studentApplicationModel.withdrawJobApplication(model, jobId, request, studentDetailsDao, studentApplicationDao, studentJobsDao);
		logger.debug("StudentJobApplicationController: withdrawApplication method: Exit");
		return model.asMap().get("view").toString();
	}
}
