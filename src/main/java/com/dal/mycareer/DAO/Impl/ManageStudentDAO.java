package com.dal.mycareer.DAO.Impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.JDBC.DeleteHandler;
import com.dal.mycareer.JDBC.InsertHandler;
import com.dal.mycareer.JDBC.JdbcManager;
import com.dal.mycareer.JDBC.SelectHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManageStudentDAO implements IManageStudentDAO {

    @Autowired
    IPrerequisiteCoursesDAO prereqDAO;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Student RegisterStudent(Student studentDetails) 
    {
        Map<String, Integer> procResults;
        try
        {
            JdbcManager jdbcManager = new InsertHandler(); 
            procResults = jdbcManager.executeProcedure("{call sp_insertStudent(?,?,?,?,?,?,?,?,?,?,?)}", "studentDetailsMapper", studentDetails, null);
            int rowsAffected = procResults.get("rowsAffected");
            int studentId = procResults.get("10");
            int userId = procResults.get("11");
            if(rowsAffected > 0 && studentId > 0 && userId > 0)
            {
                prereqDAO.addStudentCompletedPrereq(studentId, studentDetails.getCompletedCourses());
            }
            else 
            {
                logger.error("Error Occurred while registering student");
            }
        }
        catch(Exception ex)
        {
            logger.error("Error Occurred in RegisterStudent :" + ex.getMessage());
        }
        return studentDetails;
    }

    @Override
    public List<Student> getRegisteredStudents(List<Student> regStudents) throws SQLException
    {
        try 
		{
			JdbcManager jdbcManager = new SelectHandler();
			jdbcManager.executeProcedure("{call fetchRegisteredStudents()}", "studentsMapper", regStudents, null);
		} 
		catch (Exception e) 
		{
			logger.error("Exception occurred at ManageStudentDAO:getRegisteredStudents " + e.getMessage());
            throw new SQLException("Error in getRegisteredStudents");
        }
        return regStudents;
    }

    @Override
    public boolean DeleteStudent(int studentId) 
    {
        Map<String, Integer> procResult;
        boolean isDeleteSuccess;
        try
        {
            Map<String, Object> additionalParam = new HashMap<>();
            additionalParam.put("studentId", studentId);
            JdbcManager jdbcManager = new DeleteHandler();
            procResult = jdbcManager.executeProcedure("{call sp_deleteStudent(?)}", null, null, additionalParam);
            int rowsAffected = procResult.get("rowsAffected");
            if (rowsAffected > 0) 
            {
                isDeleteSuccess = true;
            } 
            else 
            {
                isDeleteSuccess = false;
                logger.error("Error Occurred while deleting student");
            }
        }
        catch(Exception ex)
        {
            isDeleteSuccess = false;
            logger.error("Error Occurred in DeleteStudent :" + ex.getMessage());
        }
        return isDeleteSuccess;
    }
    @Override
    public boolean isNewStudent(Student studentDetails) throws SQLException
    {
        boolean isNewRecord = true;
        try 
        {
            JdbcManager jdbcManager = new SelectHandler();
            Map<String, Object> inputParam = new HashMap<String, Object>();
            inputParam.put("bnrId", studentDetails.getBannerid());
            Map<String, Integer> output = jdbcManager.executeProcedure("{call checkDupicateStudent(?)}", null, null,
                    inputParam);
            if (output != null && output.size() > 0) 
            {
                if (output.get("recordExist") > 0) 
                {
                    isNewRecord = false;
                } 
                else 
                {
                    isNewRecord = true;
                }
            }
        } 
        catch (Exception ex) 
        {
            logger.error("Error Occurred in isNewStudent for BannerId :" + studentDetails.getBannerid()
                    + "Exception Details-" + ex.getMessage());
            throw new SQLException("Error in isNewStudent");
        }
        return isNewRecord;
    }
}