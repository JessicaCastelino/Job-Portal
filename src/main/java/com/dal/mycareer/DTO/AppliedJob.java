package com.dal.mycareer.DTO;

import java.io.InputStream;

public class AppliedJob {
private String id;
private InputStream document;
private String applicationStatus;
private String studentId;
private String jobId;
private String jobTitle;
private String location;
private String openPosition;
private String jobType;
private String rateOfPay;
private String hoursPerWeek;
private String applicationDeadline;
private String jobDescription;
private String additionalInformation;
private String jobStatus;
private String employeeId;
private String organization;
private String term;

public AppliedJob() {
	super();
}
public AppliedJob(String id, InputStream document, String applicationStatus, String studentId, String jobId,
		String jobTitle, String location, String openPosition, String jobType, String rateOfPay, String hoursPerWeek,
		String applicationDeadline, String jobDescription, String additionalInformation, String jobStatus,
		String employeeId, String organization, String term) {
	super();
	this.id = id;
	this.document = document;
	this.applicationStatus = applicationStatus;
	this.studentId = studentId;
	this.jobId = jobId;
	this.jobTitle = jobTitle;
	this.location = location;
	this.openPosition = openPosition;
	this.jobType = jobType;
	this.rateOfPay = rateOfPay;
	this.hoursPerWeek = hoursPerWeek;
	this.applicationDeadline = applicationDeadline;
	this.jobDescription = jobDescription;
	this.additionalInformation = additionalInformation;
	this.jobStatus = jobStatus;
	this.employeeId = employeeId;
	this.organization = organization;
	this.term = term;
}

public InputStream getDocument() {
	return document;
}
public void setDocument(InputStream document) {
	this.document = document;
}
public String getApplicationStatus() {
	return applicationStatus;
}
public void setApplicationStatus(String applicationStatus) {
	this.applicationStatus = applicationStatus;
}
public String getStudentId() {
	return studentId;
}
public void setStudentId(String studentId) {
	this.studentId = studentId;
}
public String getJobId() {
	return jobId;
}
public void setJobId(String jobId) {
	this.jobId = jobId;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getJobTitle() {
	return jobTitle;
}
public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
}
public String getOpenPosition() {
	return openPosition;
}
public void setOpenPosition(String openPosition) {
	this.openPosition = openPosition;
}
public String getJobType() {
	return jobType;
}
public void setJobType(String jobType) {
	this.jobType = jobType;
}
public String getRateOfPay() {
	return rateOfPay;
}
public void setRateOfPay(String rateOfPay) {
	this.rateOfPay = rateOfPay;
}
public String getHoursPerWeek() {
	return hoursPerWeek;
}
public void setHoursPerWeek(String hoursPerWeek) {
	this.hoursPerWeek = hoursPerWeek;
}
public String getApplicationDeadline() {
	return applicationDeadline;
}
public void setApplicationDeadline(String applicationDeadline) {
	this.applicationDeadline = applicationDeadline;
}
public String getJobDescription() {
	return jobDescription;
}
public void setJobDescription(String jobDescription) {
	this.jobDescription = jobDescription;
}
public String getAdditionalInformation() {
	return additionalInformation;
}
public void setAdditionalInformation(String additionalInformation) {
	this.additionalInformation = additionalInformation;
}
public String getJobStatus() {
	return jobStatus;
}
public void setJobStatus(String jobStatus) {
	this.jobStatus = jobStatus;
}
public String getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getOrganization() {
	return organization;
}
public void setOrganization(String organization) {
	this.organization = organization;
}
public String getTerm() {
	return term;
}
public void setTerm(String term) {
	this.term = term;
}


}
