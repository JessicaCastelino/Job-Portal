package com.dal.mycareer.model;

import java.util.List;

import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DTO.PrerequisiteCourses;
import com.dal.mycareer.imodel.IPrerequisiteCoursesModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PrerequisiteCoursesModel implements IPrerequisiteCoursesModel {

@Autowired
IPrerequisiteCoursesDAO prerequisiteCoursesDAO;
	public List<PrerequisiteCourses> getPrerequisiteCourses() {
		
		return prerequisiteCoursesDAO.getPrerequisiteCourses();
	}

}
