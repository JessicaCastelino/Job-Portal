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
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping(value = { "/studentHome" }, method = RequestMethod.GET)
	public String loadStudentHome(Model model, HttpServletRequest request) {

		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("studentHome").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		model = studentModel.fetchJobs(model, request);
		return model.asMap().get("view").toString();

	}

	@RequestMapping(value = { "/viewJob" }, method = RequestMethod.GET)
	public String viewJob(@RequestParam("id") int jobId, Model model, HttpServletRequest request) {
		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("viewJob").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		model = studentModel.viewJobs(model, jobId, request);
		return model.asMap().get("view").toString();

	}

	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest request) {

		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("submitted").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		model = studentModel.applyJob(model, file, request);
		return model.asMap().get("view").toString();

	}

	@RequestMapping(value = { "/applyJob" }, method = RequestMethod.GET)
	public String loadHome(Model model, HttpServletRequest request) {
		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("applyJob").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		return model.asMap().get("view").toString();
	}

	@RequestMapping(value = { "/withdraw" }, method = RequestMethod.GET)
	public String withdrawApplication(Model model, HttpServletRequest request) {
		model.addAttribute("reqPage", PropertiesParser.getPropertyMap().get("applyJob").toString());
		model.addAttribute("role", "student");
		roleModel = new RoleModel();
		model = roleModel.getBasePage(model, request);
		studentModel = new StudentModel();
		return model.asMap().get("view").toString();
	}

}
