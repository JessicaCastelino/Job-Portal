package com.dal.mycareer.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.imodel.IManageStudentModel;
import com.dal.mycareer.passwordgenerator.IPasswordGenerator;
import com.dal.mycareer.passwordgenerator.PasswordGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageStudentModel implements IManageStudentModel 
{
    @Autowired
    IManageStudentDAO manageStudentDAO;

    public ManageStudentModel() 
    {

    }

    public ManageStudentModel(IManageStudentDAO manageStudentDAO)
    {
        this.manageStudentDAO = manageStudentDAO;
    }

    @Override
    public Student RegisterStudent(Student studentDetails) throws SQLException
	{
        IPasswordGenerator passwordGen = new PasswordGenerator();
        studentDetails.setPassword(passwordGen.generatePassword());
        return manageStudentDAO.RegisterStudent(studentDetails);
    }

    @Override
    public List<Student> getRegisteredStudents() throws SQLException
    {
        List<Student> regStudents = new ArrayList<>();
        return manageStudentDAO.getRegisteredStudents(regStudents);
    }
    
	@Override
    public boolean DeleteStudent(int studentId) throws SQLException
    {
        return manageStudentDAO.DeleteStudent(studentId);
    }
}