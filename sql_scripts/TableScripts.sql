create table CSCI5308_8_DEVINT.userlogin
(
id integer primary key AUTO_INCREMENT,
userName varchar(50),
pwd varchar(100),
role varchar(50)
);

create table CSCI5308_8_DEVINT.employers
(
id integer primary key AUTO_INCREMENT,
title varchar(5),
firstname varchar(50),
lastname varchar(50),
email varchar(50),
designation varchar(50),
companyname varchar(50),
isActive boolean,
userid integer ,
foreign key (userid)
references userlogin(id)
);


create table CSCI5308_8_DEVINT.students
(
id integer primary key AUTO_INCREMENT,
firstname varchar(50),
lastname varchar(50),
bannerid varchar(10),
email varchar(50),
phonenumber varchar(10),
degree varchar (100),
department varchar(50),
program varchar(50),
isActive boolean,
userid integer,
foreign key (userid)
references userlogin(id)
);


create table CSCI5308_8_DEVINT.coopAdmin
(
id integer primary key AUTO_INCREMENT,
firstname varchar(50),
lastname varchar(50),
email varchar(50),
isActive boolean,
userid integer,
foreign key (userid)
references userlogin(id)
);


create table CSCI5308_8_DEVINT.jobs
(
id integer primary key AUTO_INCREMENT,
jobTitle varchar(50),
location varchar(50),
openPosition integer,
jobType varchar(50),
rateOfPay integer,
hoursPerWeek integer,
applicationDeadline datetime,
jobDescription varchar(255),
additionalInformation varchar(255),
jobStatus boolean,
employeeId integer,
foreign key (employeeId)
references employers(id)
);


create table CSCI5308_8_DEVINT.courses
(
id integer primary key AUTO_INCREMENT,
courseName varchar(50)
);

create table CSCI5308_8_DEVINT.jobRequirement
(
id integer primary key AUTO_INCREMENT,
jobId integer,
foreign key (jobId)
references jobs(id),
courseId integer,
foreign key (courseId)
references courses(id)
);

create table CSCI5308_8_DEVINT.studentRegisteredCourses
(
id integer primary key AUTO_INCREMENT,
studentId integer,
foreign key (studentId)
references students(id),
courseId integer,
foreign key (courseId)
references courses(id)
);

create table CSCI5308_8_DEVINT.appliedJobs
(
id integer primary key AUTO_INCREMENT,
document blob,
applicationStatus varchar (30),
studentId integer,
foreign key (studentId)
references students(id),
jobId integer,
foreign key (jobId)
references jobs(id)
);
