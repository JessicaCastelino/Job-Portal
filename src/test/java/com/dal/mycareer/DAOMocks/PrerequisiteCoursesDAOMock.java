package com.dal.mycareer.DAOMocks;

import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DTO.PrerequisiteCourses;

public class PrerequisiteCoursesDAOMock implements IPrerequisiteCoursesDAO 
{
    ArrayList <PrerequisiteCourses> PrerequisiteCoursesList;
    public PrerequisiteCoursesDAOMock ()
    {
        PrerequisiteCoursesList = new ArrayList<PrerequisiteCourses>();
        PrerequisiteCourses prerequisiteCourse1 = new PrerequisiteCourses();
        prerequisiteCourse1.setCourseId(1);
        prerequisiteCourse1.setCourseName("Cloud Computing");
        PrerequisiteCoursesList.add(prerequisiteCourse1);
        PrerequisiteCourses prerequisiteCourse2 = new PrerequisiteCourses();
        prerequisiteCourse2.setCourseId(2);
        prerequisiteCourse2.setCourseName("Quality Assurance");
        PrerequisiteCoursesList.add(prerequisiteCourse2);
        PrerequisiteCourses prerequisiteCourse3 = new PrerequisiteCourses();
        prerequisiteCourse3.setCourseId(3);
        prerequisiteCourse3.setCourseName("Data Science"); 
        PrerequisiteCoursesList.add(prerequisiteCourse3);
    }
    @Override
    public boolean addStudentCompletedPrereq(int studentId, List<Integer> completedCourses) 
    {
        return false;
    }

    @Override
    public List<PrerequisiteCourses> getPrerequisiteCourses() 
    {
        return PrerequisiteCoursesList;
    }

}