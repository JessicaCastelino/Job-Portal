package com.dal.mycareer.DAO.Interface;

import java.util.List;

import com.dal.mycareer.DTO.Student;

public interface IManageStudentDAO 
{
    public Student RegisterStudent(Student studentDetails);
    public List<Student> getRegisteredStudents();
}