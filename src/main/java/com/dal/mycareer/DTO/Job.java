package com.dal.mycareer.DTO;

public class Job {
private String id;
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



public Job() {
	super();
	// TODO Auto-generated constructor stub
}
public Job(String id, String jobTitle, String location, String openPosition, String jobType, String rateOfPay,
		String hoursPerWeek, String applicationDeadline, String jobDescription, String additionalInformation,
		String jobStatus, String employeeId, String organization, String term) {
	super();
	this.id = id;
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


	/*
	 * import java.util.Date; import java.util.List; public class Job { public int
	 * id; public String jobId; public String jobTitle; public String location;
	 * public String jobType; public Date applicationDeadline; public String
	 * organization; public List<Integer> selectedCourseIds;
	 */

}
