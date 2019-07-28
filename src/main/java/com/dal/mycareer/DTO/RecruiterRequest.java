package com.dal.mycareer.DTO;

public class RecruiterRequest {
String id;
String firstname;
String lastname;
String email;
String companyname;
String designation;

public RecruiterRequest() {
	super();
	// TODO Auto-generated constructor stub
}
public RecruiterRequest(String id, String firstname, String lastname, String email, String companyname,
		String designation) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.companyname = companyname;
	this.designation = designation;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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
public String getCompanyname() {
	return companyname;
}
public void setCompanyname(String companyname) {
	this.companyname = companyname;
}
public String getDesignation() 
{
	return designation;
}
public void setDesignation(String designation) 
{
	this.designation = designation;
}


}
