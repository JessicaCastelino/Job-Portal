package com.dal.mycareer.DTO;

import java.util.ArrayList;

public class StudentProfileDTO extends Student {

	private ArrayList<String> courseName;

	public ArrayList<String> getCourseName() {
		return courseName;
	}

	public void setCourseName(ArrayList<String> courseName) {
		this.courseName = courseName;
	}

	

}
