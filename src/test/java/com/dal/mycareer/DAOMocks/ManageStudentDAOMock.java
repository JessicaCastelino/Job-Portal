package com.dal.mycareer.DAOMocks;

import java.util.ArrayList;
import java.util.List;

import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DTO.Student;

public class ManageStudentDAOMock implements IManageStudentDAO 
{
    private List<Student> lstRegisteredStudent;

    public ManageStudentDAOMock()
    {
        lstRegisteredStudent = new ArrayList<>();
        Student stdnt = new Student();
        stdnt.setId(1);
        stdnt.setFirstname("Aaa");
        stdnt.setLastname("Bbb");
        stdnt.setEmail("aaabbb@gmail.com");
        stdnt.setDepartment("Computer Science");
        stdnt.setIsActive(1);
        stdnt.setBannerid("B00001");
        stdnt.setDegree("MACS");
        stdnt.setPhonenumber("90234567890");
        lstRegisteredStudent.add(stdnt);
    }

    
    public List<Student> getLstRegisteredStudent() 
    {
        return lstRegisteredStudent;
    }

    public void setLstRegisteredStudent(List<Student> lstRegisteredStudent) 
    {
        this.lstRegisteredStudent = lstRegisteredStudent;
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