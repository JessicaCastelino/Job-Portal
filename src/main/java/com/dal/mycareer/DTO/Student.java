package com.dal.mycareer.DTO;

import java.util.List;

public class Student {
	private int id;
	private String firstname;
	private String lastname;
	private String bannerid;
	private String email;
	private String phonenumber;
	private String degree;
	private String department;
	private String program;
	private int isActive;
	private List<Integer> completedCourses;

	public Student() {
		super();
	}

	public Student(int id, String firstname, String lastname, String bannerid, String email, String phonenumber,
			String degree, String department, String program, int isActive) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.bannerid = bannerid;
		this.email = email;
		this.phonenumber = phonenumber;
		this.degree = degree;
		this.department = department;
		this.program = program;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBannerid() {
		return bannerid;
	}

	public void setBannerid(String bannerid) {
		this.bannerid = bannerid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public List<Integer> getCompletedCourses() {
		return completedCourses;
	}

	public void setCompletedCourses(List<Integer> completedCourses) {
		this.completedCourses = completedCourses;
	}

}
