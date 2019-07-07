package com.dal.mycareer.model;

import java.util.List;

import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.imodel.IManageStudentModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageStudentModel implements IManageStudentModel {
    @Autowired
    IManageStudentDAO manageStudentDAO;

    @Override
    public Student RegisterStudent(Student studentDetails) 
	{
        return manageStudentDAO.RegisterStudent(studentDetails);
    }

    @Override
    public List<Student> getRegisteredStudents()
    {
        return manageStudentDAO.getRegisteredStudents();
    }
}