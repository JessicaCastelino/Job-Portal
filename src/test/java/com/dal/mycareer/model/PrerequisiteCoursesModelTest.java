package com.dal.mycareer.model;

import java.util.List;
import static org.junit.Assert.assertEquals;
import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DAOMocks.PrerequisiteCoursesDAOMock;
import com.dal.mycareer.DTO.PrerequisiteCourses;
import com.dal.mycareer.model.PrerequisiteCoursesModel;

import org.junit.Before;
import org.junit.Test;

public class PrerequisiteCoursesModelTest
{
    IPrerequisiteCoursesDAO prerequisiteCoursesDAO;

    @Before
    public void setUp()
    {
        prerequisiteCoursesDAO =  new PrerequisiteCoursesDAOMock();
    }
    @Test
    public void getPrerequisiteCourses()
    {
    int expectedCoursesCount =3;
    PrerequisiteCoursesModel prerequisiteCoursesModel =  new PrerequisiteCoursesModel(prerequisiteCoursesDAO);
    List <PrerequisiteCourses> fetchedPrerequisiteCourses = prerequisiteCoursesModel.getPrerequisiteCourses();
    assertEquals(expectedCoursesCount,fetchedPrerequisiteCourses.size());
    } 
}