package com.dal.mycareer.model;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DTO.PrerequisiteCourses;
import com.dal.mycareer.imodel.IPrerequisiteCoursesModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PrerequisiteCoursesModel implements IPrerequisiteCoursesModel {

@Autowired
IPrerequisiteCoursesDAO prerequisiteCoursesDAO;

static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

public PrerequisiteCoursesModel ()
{

}
public PrerequisiteCoursesModel (IPrerequisiteCoursesDAO prerequisiteCoursesDAO)
{
this.prerequisiteCoursesDAO = prerequisiteCoursesDAO;
}
public List<PrerequisiteCourses> getPrerequisiteCourses() 
{
	logger.info("BL: getPrerequisiteCourses method started");
	List <PrerequisiteCourses> lstPrerequisteCourses=new ArrayList<PrerequisiteCourses>();
	return prerequisiteCoursesDAO.getPrerequisiteCourses(lstPrerequisteCourses);
}

}
