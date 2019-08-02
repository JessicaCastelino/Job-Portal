package com.dal.mycareer.DTOMapper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dal.mycareer.DTO.Student;

public class StudentDetailsMapper implements IDTOMapper 
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public int[] mapObjectToStatement(Object dtoObject, CallableStatement callStatement,
            Map<String, Object> additionalParam) 
    {
        int[] outparamIndex = null;
        Student student = (Student) dtoObject;
        try
        {
            callStatement.setString("fname", student.getFirstname());
            callStatement.setString("lname", student.getLastname());
            callStatement.setString("bannerid", student.getBannerid());
            callStatement.setString("email", student.getEmail());
            callStatement.setString("phone", student.getPhonenumber());
            callStatement.setString("degree", student.getDegree());
            callStatement.setString("dept", student.getDepartment());
            callStatement.setString("pgm", student.getProgram());
            callStatement.setString("pswrd", student.getPassword());
            callStatement.registerOutParameter(10, java.sql.Types.INTEGER);
            callStatement.registerOutParameter(11, java.sql.Types.INTEGER);
            outparamIndex = new int[] { 10, 11 };
        }
        catch(Exception ex)
        {
            logger.error("Error Occurred in mapObjectToStatement for StudentDetails :" + ex.getMessage());
        }
        return outparamIndex;
    }

    @Override
    public Object mapStatementtoObject(ResultSet resultSet, Object studentInfo) {
        Student studentDetails;
        studentDetails = (Student) studentInfo;
        try
        {
            while(resultSet.next())
            {
                studentDetails.setId(resultSet.getInt("id"));
                studentDetails.setFirstname(resultSet.getString("firstname"));
                studentDetails.setLastname(resultSet.getString("lastname"));
                studentDetails.setBannerid(resultSet.getString("bannerid"));
                studentDetails.setEmail(resultSet.getString("email"));
                studentDetails.setRequiredCourses(resultSet.getString("requiredCourses"));
            }
            return studentDetails;
        }
        catch(Exception ex)
        {
            logger.error("Error Occurred in mapStatementtoObject for studentDetails :" + ex.getMessage());
        }
        return null;
    }
    
}