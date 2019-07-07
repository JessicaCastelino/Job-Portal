package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManageStudentDAO implements IManageStudentDAO {

    @Autowired
    IPrerequisiteCoursesDAO prereqDAO;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public Student RegisterStudent(Student studentDetails) 
    {
        CallableStatement callStatement = null;
        Connection con = null;
        try 
        {
            con = DatabaseConnection.getConnection();
            callStatement = con.prepareCall("{call sp_insertStudent(?,?,?,?,?,?,?,?,?,?)}");
            callStatement.setString("fname", studentDetails.getFirstname());
            callStatement.setString("lname", studentDetails.getLastname());
            callStatement.setString("bannerid", studentDetails.getBannerid());
            callStatement.setString("email", studentDetails.getEmail());
            callStatement.setString("phone", studentDetails.getPhonenumber());
            callStatement.setString("degree", studentDetails.getDegree());
            callStatement.setString("dept", studentDetails.getDepartment());
            callStatement.setString("pgm", studentDetails.getProgram());
            callStatement.registerOutParameter(9, java.sql.Types.INTEGER);
            callStatement.registerOutParameter(10, java.sql.Types.INTEGER);
            int rowsAffected = callStatement.executeUpdate();
            if (rowsAffected > 0) 
            {
                int studentId = callStatement.getInt(9);
                int userId = callStatement.getInt(10);
                prereqDAO.addStudentCompletedPrereq(studentId, studentDetails.getCompletedCourses());
            } 
            else 
            {
                LOGGER.error("Error Occurred while registering student");
            }
        } 
        catch (Exception ex) 
        {
            LOGGER.error("Error Occurred in RegisterStudent :" + ex.getMessage());
        }

        return studentDetails;
    }

}