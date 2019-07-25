package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import com.dal.mycareer.DTO.PrerequisiteCourses;
import com.dal.mycareer.imodel.IPrerequisiteCoursesModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrerequisiteCoursesController 
{
	static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@Autowired
	IPrerequisiteCoursesModel prerequisiteCoursesModel;

	@ResponseBody
	@RequestMapping(value = "/getPrerequisiteCourses", method = RequestMethod.GET)
	public List<PrerequisiteCourses> getPrerequisitCourses() 
	{
		logger.info("Controller: Inside getPrerequisitCourses method");
		return prerequisiteCoursesModel.getPrerequisiteCourses();
	}
}
