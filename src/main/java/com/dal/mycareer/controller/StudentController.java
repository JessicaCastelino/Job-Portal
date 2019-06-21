package com.dal.mycareer.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Impl.StudentDAO;
import com.dal.mycareer.DAO.Interface.IStudentDAO;
import com.dal.mycareer.DTO.AppliedJob;
import com.dal.mycareer.DTO.Job;
import com.dal.mycareer.DTO.Student;

@Controller
public class StudentController {
	private static List<Job> jobs = new ArrayList<Job>();
	private static List<AppliedJob> appliedJobs = new ArrayList<AppliedJob>();
	private static final String SESSION_NAME = "sessionName";
	static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	IStudentDAO dao = null;
	Student student = null;

	@RequestMapping(value = { "/studentHome" }, method = RequestMethod.GET)
	public String loadStudentHome(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String userSessionName = (String) session.getAttribute(SESSION_NAME);
		if (userSessionName != "" || userSessionName != null) {
			dao = new StudentDAO();
			student = dao.getStudentDetails(userSessionName);
			// System.out.println("ID: " + student.getFirstname() + " " + student.getId());
			jobs = dao.getAllJobList();
			appliedJobs = dao.getAppliedJobList(student.getId());
			model.addAttribute("jobs", jobs);
			model.addAttribute("appliedJobs", appliedJobs);
			return "studentView/homepage";

		} else {
			return "logout";
		}

	}

	@RequestMapping(value = { "/viewJob" }, method = RequestMethod.GET)
	public String viewJob(@RequestParam("id") int jobId, Model model) {
		System.out.println("JOB ID:" + jobId);
		for (Job job : jobs) {
			if (job.getId() == jobId)
				model.addAttribute("job", job);
		}
		return "studentView/viewJob";
	}

	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		InputStream inputStream = null;

		dao = new StudentDAO();
		if (file != null) {
			System.out.println(String.format("File name %s", file.getOriginalFilename()));
			try {
				inputStream = file.getInputStream();
				int i = dao.applyForJob(inputStream);
				if (i == 1) {
					System.out.println("File Uploaded..");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "studentView/submitted";
	}

	@RequestMapping(value = { "/applyJob" }, method = RequestMethod.GET)
	public String loadHome() {
		return "studentView/applyJob";
	}

}
