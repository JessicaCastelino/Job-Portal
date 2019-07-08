package com.dal.mycareer.model;

import java.util.List;

import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.imodel.IManageStudentModel;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageStudentModel implements IManageStudentModel {
    @Autowired
    IManageStudentDAO manageStudentDAO;

    @Override
    public Student RegisterStudent(Student studentDetails) 
	{
        Student studDetails = studentDetails;
        IPasswordGenerator passwordGen = new PasswordGenerator();
        studDetails.setPassword(passwordGen.generatePassword());
        return manageStudentDAO.RegisterStudent(studentDetails);
    }

    @Override
    public List<Student> getRegisteredStudents()
    {
        return manageStudentDAO.getRegisteredStudents();
    }
	   @Override
    public boolean DeleteStudent(int studentId) 
    {
        return manageStudentDAO.DeleteStudent(studentId);
    }
}