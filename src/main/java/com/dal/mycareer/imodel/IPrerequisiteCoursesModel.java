package com.dal.mycareer.imodel;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.PrerequisiteCourses;

public interface IPrerequisiteCoursesModel {

	public List<PrerequisiteCourses> getPrerequisiteCourses() throws SQLException;
}
