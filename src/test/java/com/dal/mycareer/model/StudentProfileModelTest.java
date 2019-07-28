package com.dal.mycareer.model;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.dal.mycareer.DAO.Impl.StudentProfileDAO;
import com.dal.mycareer.DAO.Interface.IStudentProfileDAO;
import com.dal.mycareer.DTO.StudentProfileDTO;

public class StudentProfileModelTest {

	@Test
	public void testGetStudentProfile() {

		// Mocked Object Creation.
		IStudentProfileDAO mockStudentProfileDAO = mock(StudentProfileDAO.class);
		Model mockModel = new ExtendedModelMap();
		
		// Data Preparation
		ArrayList<String> courseList = new ArrayList<String>();
		courseList.add("Machine Learning");
		courseList.add("Data Science");
		StudentProfileDTO spDTO = new StudentProfileDTO();
		spDTO.setCourseName(courseList);
		Mockito.when(mockStudentProfileDAO.getStudentProfile("mock@mock.com")).thenReturn(spDTO);
		
		// Object Creation
		StudentProfileModel spm = new StudentProfileModel();

		// Output
		Model output = spm.getStudentProfile(mockModel, "mock@mock.com", mockStudentProfileDAO);
		
		StudentProfileDTO outputDTO = (StudentProfileDTO)output.asMap().get("profileData");
		
		
		//Assertion
		Assert.assertEquals("Machine Learning", outputDTO.getCourseName().get(0));
		Assert.assertEquals("Data Science", outputDTO.getCourseName().get(1));

	}

}
