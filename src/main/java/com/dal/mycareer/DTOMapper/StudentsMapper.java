package com.dal.mycareer.DTOMapper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.dal.mycareer.DTO.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentsMapper implements IDTOMapper 
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public int[] mapObjectToStatement(Object jobDetails, CallableStatement callStatement,
            Map<String, Object> additionalParam) 
    {
        // This method will be implemented, if bulk insert of Students feature is added in future
        return null;
    }

    @Override
    public Object mapStatementtoObject(ResultSet resultSet, Object dtoObject) 
    {
        List<Student> students = (List<Student>) dtoObject;
        Student student;
        try
        {
            while (resultSet.next())
            {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFirstname(resultSet.getString("firstname"));
                student.setLastname(resultSet.getString("lastname"));
                student.setBannerid(resultSet.getString("bannerid"));
                student.setEmail(resultSet.getString("email"));
                student.setRequiredCourses(resultSet.getString("requiredCourses"));
                students.add(student);
            }
        }
        catch(Exception ex)
        {
            logger.error("Error Occurred in mapStatementtoObject for Students :" + ex.getMessage());
        }
        return students;
    }
    
}