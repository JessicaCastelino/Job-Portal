package com.dal.mycareer.DAO.Interface;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.PrerequisiteCourses;

public interface IPrerequisiteCoursesDAO {

	public List<PrerequisiteCourses> getPrerequisiteCourses(List <PrerequisiteCourses> lstPrerequisteCourses) throws SQLException;
	public boolean addStudentCompletedPrereq(int studentId, List<Integer> completedCourses);
	public boolean insertJobPrerequisiteCourses(int jobId, List<Integer> prerequisiteCourses) throws SQLException;
}
