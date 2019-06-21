package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.PrerequisiteCourses;
@Repository
public class PrerequisiteCoursesDAO implements IPrerequisiteCoursesDAO {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());	
	public List<PrerequisiteCourses> getPrerequisiteCourses() {
		CallableStatement callStatement = null;
		Connection con= null;
		List <PrerequisiteCourses> lstPrerequisteCourses = null;
		PrerequisiteCourses prerequisiteCourse = null;
		try {
			con = DatabaseConnection.getConnection();
			 callStatement = con.prepareCall("{call sp_getPrerequisiteCourses()}"); 
			 ResultSet courses = callStatement.executeQuery();
			 lstPrerequisteCourses = new ArrayList<PrerequisiteCourses>();
			 while (courses.next())
			 {
				 prerequisiteCourse = new PrerequisiteCourses();
				 prerequisiteCourse.courseId = courses.getInt("courseId");
				 prerequisiteCourse.CourseName = courses.getString("courseName");
				 lstPrerequisteCourses.add(prerequisiteCourse);
			 }
		}
		catch (Exception ex)
		{
			LOGGER.error( "Error Occurred in getPrerequisiteCourses :" + ex.getMessage());
		}
		return lstPrerequisteCourses;
	}

	
}
