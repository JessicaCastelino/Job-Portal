package com.dal.mycareer.DAO.Impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dal.mycareer.DAO.Interface.IManageStudentDAO;
import com.dal.mycareer.DAO.Interface.IPrerequisiteCoursesDAO;
import com.dal.mycareer.DBConnection.DatabaseConnection;
import com.dal.mycareer.DTO.Student;
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

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public Student RegisterStudent(Student studentDetails) 
    {
        CallableStatement callStatement = null;
        Connection con = null;
        try 
        {
            con = DatabaseConnection.getConnection();
            if(isNewStudent(studentDetails))
            {
            callStatement = con.prepareCall("{call sp_insertStudent(?,?,?,?,?,?,?,?,?,?,?)}");
            callStatement.setString("fname", studentDetails.getFirstname());
            callStatement.setString("lname", studentDetails.getLastname());
            callStatement.setString("bannerid", studentDetails.getBannerid());
            callStatement.setString("email", studentDetails.getEmail());
            callStatement.setString("phone", studentDetails.getPhonenumber());
            callStatement.setString("degree", studentDetails.getDegree());
            callStatement.setString("dept", studentDetails.getDepartment());
            callStatement.setString("pgm", studentDetails.getProgram());
            callStatement.setString("pswrd", studentDetails.getPassword());
            callStatement.registerOutParameter(10, java.sql.Types.INTEGER);
            callStatement.registerOutParameter(11, java.sql.Types.INTEGER);
            int rowsAffected = callStatement.executeUpdate();
            if (rowsAffected > 0) 
            {
                int studentId = callStatement.getInt(10);
                int userId = callStatement.getInt(11);
                prereqDAO.addStudentCompletedPrereq(studentId, studentDetails.getCompletedCourses());
            } 
            else 
            {
                LOGGER.error("Error Occurred while registering student");
            }
        }
        } 
        catch (Exception ex) 
        {
            LOGGER.error("Error Occurred in RegisterStudent :" + ex.getMessage());
        }

        return studentDetails;
    }
    @Override
    public List<Student> getRegisteredStudents()
    {
        CallableStatement callStatement = null;
        Connection con = null;
        Student student = null;
        List <Student> registeredStudentList = new ArrayList<Student>();
        try
        {
            
            con = DatabaseConnection.getConnection();
            callStatement = con.prepareCall("{call fetchRegisteredStudents()}");
            ResultSet regStudentsSet = callStatement.executeQuery();
            while (regStudentsSet.next())
            {
                student = new Student();
                student.setId(regStudentsSet.getInt("id"));
                student.setFirstname(regStudentsSet.getString("firstname"));
                student.setLastname(regStudentsSet.getString("lastname"));
                student.setBannerid(regStudentsSet.getString("bannerid"));
                student.setEmail(regStudentsSet.getString("email"));
                student.setRequiredCourses(regStudentsSet.getString("requiredCourses"));
                registeredStudentList.add(student);
            }
        }
        catch (Exception ex)
        {
            LOGGER.error( "Error Occurred in getRegisteredStudents :" + ex.getMessage());
        }
        finally
		{
            try 
            {
				callStatement.close();
            }
             catch (SQLException e) 
             {
				e.printStackTrace();
			}
		}
        return registeredStudentList;
    }
   @Override
    public boolean DeleteStudent(int studentId) {
        CallableStatement callStatement = null;
        Connection con = null;
        boolean isDeleteSuccess = false;
        try 
        {
            con = DatabaseConnection.getConnection();
            callStatement = con.prepareCall("{call sp_deleteStudent(?)}");
            callStatement.setInt("studentId", studentId);
            int rowsAffected = callStatement.executeUpdate();
            if (rowsAffected > 0) 
            {
                isDeleteSuccess = true;
            } 
            else 
            {
                isDeleteSuccess = false;
                LOGGER.error("Error Occurred while deleting student");
            }
        } 
        catch (Exception ex) 
        {
            isDeleteSuccess = false;
            LOGGER.error("Error Occurred in DeleteStudent :" + ex.getMessage());
        }

        return isDeleteSuccess;
    }

    public boolean isNewStudent(Student studentDetails)
    {

        boolean isNewRecord = true;
        try
       {
           JdbcManager jdbcManager = new SelectHandler();
           Map<String, Object> inputParam = new HashMap<String, Object>();
           inputParam.put("bnrId", studentDetails.getBannerid());
           Map <String,Integer> output = jdbcManager.executeProcedure("{call checkDupicateStudent(?)}", null, null, inputParam);
          if(output !=null && output.size() > 0)
          {
            if(output.get("recordExist") > 0)
            {
                isNewRecord = false;
            }
            else
            {
                isNewRecord = true;
            }
          }
       }
       catch(Exception ex)
       {
        LOGGER.error("Error Occurred in isNewStudent for BannerId :" + studentDetails.getBannerid() +"Exception Details-"+ ex.getMessage());
       }
        return isNewRecord;
    }
}