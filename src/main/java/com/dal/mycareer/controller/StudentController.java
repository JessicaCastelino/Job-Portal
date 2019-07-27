package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Impl.StudentDAO;
import com.dal.mycareer.DAO.Interface.IStudentDAO;
import com.dal.mycareer.DTO.Filter;
import com.dal.mycareer.DTO.UserLogin;
import com.dal.mycareer.imodel.IRoleModel;
import com.dal.mycareer.imodel.IStudentModel;
import com.dal.mycareer.model.RoleModel;
import com.dal.mycareer.model.StudentModel;
import com.dal.mycareer.propertiesparser.PropertiesParser;

@Controller
public class StudentController {
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	IStudentModel studentModel = null;
	IRoleModel roleModel = null;
	IStudentDAO dao = new StudentDAO();
	private static final String FILTER = "filter";

	@RequestMapping(value = { "/studentHome" }, method = RequestMethod.GET)
	public String loadStudentHome(Model model, HttpServletRequest request) {
		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("studentHome").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		model = studentModel.fetchJobs(model, request, dao);
		return model.asMap().get("view").toString();
	}

	@RequestMapping(value = { "/viewJob" }, method = RequestMethod.GET)
	public String viewJob(@RequestParam("id") int jobId, Model model, HttpServletRequest request) {
		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("viewJob").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		model = studentModel.viewJobs(model, jobId, request, dao);
		return model.asMap().get("view").toString();

	}

	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request,@RequestParam int jobId) {

		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("submitted").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		model = studentModel.applyJob(model, file, request,jobId, dao);
		return model.asMap().get("view").toString();

	}

	@RequestMapping(value = { "/applyJob" }, method = RequestMethod.GET)
	public String loadHome(Model model, HttpServletRequest request, @RequestParam int jobId) {
		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("applyJob").toString());
		model.addAttribute("role", "student");
		model = studentModel.jobApplicationExists(model, request,jobId, dao);
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		model.addAttribute("jobId",jobId);
		return model.asMap().get("view").toString();
	}

	@RequestMapping(value = { "/withdraw" }, method = RequestMethod.GET)
	public String withdrawApplication(@RequestParam("id") int jobId, Model model, HttpServletRequest request) {
		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("studentHome").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		model = studentModel.withdrawApplication(model, jobId, request, dao);
		return model.asMap().get("view").toString();
	}
	@RequestMapping(value = { "/filter" }, method = RequestMethod.GET)
	public String loadStudentHome(Model model, HttpServletRequest request, @ModelAttribute(FILTER) Filter filter) {

		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("studentHome").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		System.out.println(filter.getLocation());
		System.out.println(filter.getJobType());
		model = studentModel.filterJobs(model, request, filter.getLocation(), filter.getJobType(), dao);
		return model.asMap().get("view").toString();

	}

}
