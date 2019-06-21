package com.dal.mycareer.imodel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface IStudentModel {

	public Model fetchJobs(Model model, HttpServletRequest request);

	public Model viewJobs(Model model, int jobId, HttpServletRequest request);

	public Model applyJob(Model model, MultipartFile file, HttpServletRequest request);

}
