package com.dal.mycareer.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dal.mycareer.DAO.Impl.FileDAO;

@Controller
public class FileUploadController{
	
	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		InputStream inputStream = null;
		
		FileDAO dao=new FileDAO();
		if(file!=null)
		{
		 System.out.println(String.format("File name %s", file.getOriginalFilename()));
		 try {
			 inputStream = file.getInputStream();
			 int i=dao.uploadFile(inputStream);
			 if(i==1)
			 {
				 System.out.println("File Uploaded..");
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		   
		return "homepage";
	}
	@RequestMapping(value = { "/applyJob" }, method = RequestMethod.GET)
	public String loadHome() {
		return "studentView/applyJob";
	}
	
}
