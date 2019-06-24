package com.dal.mycareer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import com.dal.mycareer.DTO.PrerequisiteCourses;
import com.dal.mycareer.imodel.IPrerequisiteCoursesModel;

@Controller
public class PrerequisiteCoursesController {
@Autowired
IPrerequisiteCoursesModel prerequisiteCoursesModel;
	
	@ResponseBody
	@RequestMapping( value="/getPrerequisiteCourses", method=RequestMethod.GET)
	public List<PrerequisiteCourses> getPrerequisitCourses() {
		
		return prerequisiteCoursesModel.getPrerequisiteCourses();
	}
}
