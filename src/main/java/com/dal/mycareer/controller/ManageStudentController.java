package com.dal.mycareer.controller;

import javax.servlet.http.HttpServletRequest;

import com.dal.mycareer.DTO.Student;
import com.dal.mycareer.imodel.IManageStudentModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManageStudentController 
{
    @Autowired
    IManageStudentModel manageStudentModel;

    @ResponseBody
	@RequestMapping( value="/registerstudent", method=RequestMethod.POST)
    public Student RegisterStudent(@RequestBody Student studentDetails, HttpServletRequest request)
    {
        String currentUser = (String) request.getSession().getAttribute("sessionName");
		return manageStudentModel.RegisterStudent(studentDetails);
    }
}