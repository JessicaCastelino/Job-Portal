package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.PrerequisiteCourses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PrerequisiteCoursesDAO implements IPrerequisiteCoursesDAO 
{
	CallableStatement callStatement = null;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());	
	public List<PrerequisiteCourses> getPrerequisiteCourses(List <PrerequisiteCourses> lstPrerequisteCourses) 
	{
		Connection con= null;
		PrerequisiteCourses prerequisiteCourse = null;
		ResultSet courses = null;
		logger.info("DL: getPrerequisiteCourses method started");
		try 
		{
			con = DatabaseConnection.getConnection();
			callStatement = con.prepareCall("{call sp_getPrerequisiteCourses()}"); 
			courses = callStatement.executeQuery();
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
			logger.error( "Error Occurred in getPrerequisiteCourses :" + ex.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(courses, callStatement, con);
		}
		return lstPrerequisteCourses;
	}

	@Override
	public boolean addStudentCompletedPrereq(int studentId, List<Integer> completedCourses) 
	{
		boolean isSuccess = false;
		Connection con= null;
		logger.info("DL: addStudentCompletedPrereq method started");
		try
		{
			con = DatabaseConnection.getConnection();
			String courses = completedCourses.stream().map(n-> n.toString()).collect(Collectors.joining(","));
			callStatement = con.prepareCall("{call insertStudentCompletedCourses(?,?)}");
			callStatement.setString("courseIds",courses); 
			callStatement.setInt("studentId", studentId);
			int rowAffected = callStatement.executeUpdate();
			if (rowAffected > 0)
			{
				isSuccess = true;
			}
			else
		 	{
				isSuccess = false;
				logger.error( "Error Occurred while recording the completed courses for student");
		 	}	 
		 }
		catch (Exception ex)
		{
			logger.error( "Error Occurred in addStudentCompletedPrereq :" + ex.getMessage());
		}
		finally
		{
			DatabaseConnection.closeDatabaseComponents(callStatement);
		}
		return isSuccess;
	}	
}
