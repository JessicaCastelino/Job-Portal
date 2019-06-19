package com.dal.mycareer.model;

public class Job {
private String jobId;
private String position;
private String organization;
private String location;
private String term;
private String deadline;


//removelater

public String getJobId() {
	return jobId;
}
public Job(String jobId, String position, String organization, String location, String term, String deadline) {
	super();
	this.jobId = jobId;
	this.position = position;
	this.organization = organization;
	this.location = location;
	this.term = term;
	this.deadline = deadline;
}
public void setJobId(String jobId) {
	this.jobId = jobId;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
public String getOrganization() {
	return organization;
}
public void setOrganization(String organization) {
	this.organization = organization;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getTerm() {
	return term;
}
public void setTerm(String term) {
	this.term = term;
}
public String getDeadline() {
	return deadline;
}
public void setDeadline(String deadline) {
	this.deadline = deadline;
}


}
