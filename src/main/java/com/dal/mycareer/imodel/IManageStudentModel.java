package com.dal.mycareer.imodel;

import java.util.List;

import com.dal.mycareer.DTO.Student;

public interface IManageStudentModel 
{
    public Student RegisterStudent(Student studentDetails);
    public List<Student> getRegisteredStudents ();
}