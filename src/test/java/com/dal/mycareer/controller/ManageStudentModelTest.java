package com.dal.mycareer.controller;

import com.dal.mycareer.DAOMocks.ManageStudentDAOMock;
import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.imodel.IManageStudentModel;
import com.dal.mycareer.model.ManageStudentModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManageStudentModelTest
{
    IManageStudentDAO manageStudentMockDAO;
    IManageStudentModel manageStudentModel;

    @Before
    public void setUp ()
    {
        manageStudentMockDAO = new ManageStudentDAOMock();
        manageStudentModel = new ManageStudentModel(manageStudentMockDAO);
    }

    @Test
    public void RegisterStudentSuccessfulRegistrationTest()
    {
        Student stdnt = new Student();
        stdnt.setFirstname("Abc");
        stdnt.setLastname("Xyz");
        stdnt.setEmail("abcxyz@gmail.com");
        stdnt.setDepartment("Computer Science");
        stdnt.setIsActive(1);
        stdnt.setBannerid("B00001");
        stdnt.setDegree("MACS");
        stdnt.setPhonenumber("90234567890");
        Student regStudent = manageStudentModel.RegisterStudent(stdnt);
        Assert.assertTrue("Student registration failed", regStudent.getId() != 0);
    }
}