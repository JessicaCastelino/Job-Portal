package com.dal.mycareer.DAO.Interface;

import java.sql.SQLException;
import java.util.List;

import com.dal.mycareer.DTO.Student;

public interface IManageStudentDAO 
{
    public Student RegisterStudent(Student studentDetails) throws SQLException;
    public List<Student> getRegisteredStudents(List<Student> regStudents) throws SQLException;
    public boolean DeleteStudent(int studentId) throws SQLException;
    public boolean isNewStudent(Student studentDetails) throws SQLException;
}