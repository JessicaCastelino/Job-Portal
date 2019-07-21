package com.dal.mycareer.DAOMocks;

import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DTO.Student;

public class ManageStudentDAOMock implements IManageStudentDAO 
{
    List<Student> lstRegisteredStudent;

    public ManageStudentDAOMock()
    {
        lstRegisteredStudent = new ArrayList<>();
    }

    @Override
    public boolean DeleteStudent(int studentId) 
    {
        return lstRegisteredStudent.removeIf(student -> student.getId() == studentId);
    }

    @Override
    public Student RegisterStudent(Student studentDetails) 
    {
        studentDetails.setId(lstRegisteredStudent.size() + 1);
        lstRegisteredStudent.add(studentDetails);
        return studentDetails;
    }

    @Override
    public List<Student> getRegisteredStudents() 
    {
        return lstRegisteredStudent;
    }
}