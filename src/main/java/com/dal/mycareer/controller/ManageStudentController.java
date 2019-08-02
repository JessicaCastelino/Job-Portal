package com.dal.mycareer.controller;

import java.lang.invoke.MethodHandles;

import javax.servlet.http.HttpServletRequest;

import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.emailengine.IStudentOnboardEmail;
import com.dal.mycareer.emailengine.StudentOnboardEmailImpl;
import com.dal.mycareer.imodel.IManageStudentModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManageStudentController 
{
    @Autowired
    IManageStudentModel manageStudentModel;

    static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @RequestMapping("/students")
	public String getRegisteredStudents(ModelMap model)
	{
        model.addAttribute("registeredStudents", manageStudentModel.getRegisteredStudents());
        return "students";
    }
    
    @ResponseBody
	@RequestMapping( value="/registerstudent", method=RequestMethod.POST)
    public Student RegisterStudent(@RequestBody Student studentDetails, HttpServletRequest request)
    {
        Student student = manageStudentModel.RegisterStudent(studentDetails);
        IStudentOnboardEmail onboardEmail = new StudentOnboardEmailImpl();
        onboardEmail.studentOnboardEmail(student.getEmail(), student.getFirstname(), student.getPassword(), student.getEmail());
		return student;
    }

    @ResponseBody
	@RequestMapping( value="/deletestudent", method=RequestMethod.DELETE)
    public boolean DeleteStudent(@RequestParam(name = "id") int studentId, HttpServletRequest request)
    {
		return manageStudentModel.DeleteStudent(studentId);
    }
}