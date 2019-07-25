package com.dal.mycareer.controller;

import java.util.List;

import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DAOMocks.ManageStudentDAOMock;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.imodel.IManageStudentModel;
import com.dal.mycareer.model.ManageStudentModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

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
        stdnt.setBannerid("B00002");
        stdnt.setDegree("MACS");
        stdnt.setPhonenumber("90234567890");
        Student regStudent = manageStudentModel.RegisterStudent(stdnt);
        Assert.assertTrue("Student registration failed", regStudent.getId() != 0);
    }

    @Test
    public void getRegisteredStudentsCountTest()
    {
        int expectedResult = 1;
        List<Student> students = manageStudentModel.getRegisteredStudents();
        int actualResult = students.size();
        Assert.assertTrue("Test by count of registered students failed", expectedResult == actualResult);
    }

    @Test
    public void getRegisteredStudentsAfterRegistrationTest()
    {
        Student newStdnt = new Student();
        newStdnt.setFirstname("Ccc");
        newStdnt.setLastname("Ddd");
        newStdnt.setEmail("cccddd@gmail.com");
        newStdnt.setDepartment("Computer Science");
        newStdnt.setIsActive(1);
        newStdnt.setBannerid("B00003");
        newStdnt.setDegree("MACS");
        newStdnt.setPhonenumber("90234567890");
        manageStudentModel.RegisterStudent(newStdnt);
        List<Student> students = manageStudentModel.getRegisteredStudents();
        int totalStudents = students.size();
        Student addedStudent = students.get(totalStudents - 1);
        Assert.assertTrue("Test by student data of registered students failed", new ReflectionEquals(newStdnt).matches(addedStudent));
    }

    @Test
    public void DeleteStudentTest()
    {
        Boolean studentDeleted = manageStudentModel.DeleteStudent(1);
        Assert.assertEquals("Test by count of registered students", studentDeleted, true);
    }

    @Test
    public void DeleteStudentCountTest()
    {
        int expectedResult = 0;
        manageStudentModel.DeleteStudent(1);
        List<Student> students = manageStudentModel.getRegisteredStudents();
        int actualResult = students.size();
        Assert.assertTrue("Test by count of registered students after deletion", expectedResult == actualResult);
    }

    @Test
    public void DeleteStudentAndFindTest()
    {
        Student newStdnt = new Student();
        newStdnt.setFirstname("Eee");
        newStdnt.setLastname("Fff");
        newStdnt.setEmail("cccddd@gmail.com");
        newStdnt.setDepartment("Computer Science");
        newStdnt.setIsActive(1);
        newStdnt.setBannerid("B00003");
        newStdnt.setDegree("MACS");
        newStdnt.setPhonenumber("90234567890");
        Student addedStudent = manageStudentModel.RegisterStudent(newStdnt);
        List<Student> students = manageStudentModel.getRegisteredStudents();
        Student studentBeforeDeletion = students.stream().filter(stdnt -> stdnt.getId() == addedStudent.getId()).findFirst().orElse(null);
        manageStudentModel.DeleteStudent(addedStudent.getId());
        Student studentAfterDeletion = students.stream().filter(stdnt -> stdnt.getId() == addedStudent.getId()).findFirst().orElse(null);
        Assert.assertTrue("Test by finding the index of deleted student failed", studentBeforeDeletion != null && studentAfterDeletion == null);
    }
}