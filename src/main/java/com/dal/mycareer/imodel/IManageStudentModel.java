package com.dal.mycareer.imodel;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.Student;

public interface IManageStudentModel 
{
    public Student RegisterStudent(Student studentDetails) throws SQLException;
    public List<Student> getRegisteredStudents () throws SQLException;
	public boolean DeleteStudent(int studentId) throws SQLException;
}