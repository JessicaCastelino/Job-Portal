package com.dal.mycareer.DTO;

public class PrerequisiteCourses 
{

	public Integer courseId;
	public String CourseName;

	public Integer getCourseId() 
	{
		return courseId;
	}

	public void setCourseId(Integer courseId) 
	{
		this.courseId = courseId;
	}

	public String getCourseName() 
	{
		return CourseName;
	}

	public void setCourseName(String courseName) 
	{
		CourseName = courseName;
	}
}
