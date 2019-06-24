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

ALTER TABLE appliedJobs MODIFY COLUMN document longblob;
ALTER TABLE jobs ADD term varchar(10);
ALTER TABLE jobs ADD organization varchar(50);


DROP procedure IF EXISTS `isValidLogin`;
DELIMITER //
	 CREATE PROCEDURE isValidLogin(IN userna varchar(50), IN pass varchar(100), IN rle varchar(50), OUT uid int(11))
	   BEGIN
	   SELECT id INTO uid FROM userlogin where userName = userna AND pwd = pass AND role = rle;
	   END //
DELIMITER ;

DROP procedure IF EXISTS `employerRequest`;
DELIMITER //
	 CREATE PROCEDURE employerRequest(IN etitle varchar(5), IN efn varchar(50), IN eln varchar(50), IN eemail varchar(50), IN desig varchar(50), IN company varchar(50), IN isAct tinyint(1))
	   BEGIN
	   INSERT into employers(title,firstname,lastname,email,designation,companyname,isActive) values(etitle,efn,eln,eemail,desig,company,isAct);
	   END //
DELIMITER ;

DROP procedure IF EXISTS `studprofile`;
DELIMITER //
	 CREATE PROCEDURE studprofile(IN inemail varchar(50))
	   BEGIN
	   SELECT * FROM(SELECT stud.firstname, stud.lastname, stud.email, stud.bannerid, stud.phonenumber, stud.degree, stud.department, stud.program, src.courseId FROM students as stud inner join studentRegisteredCourses as src  on src.studentId = stud.id where stud.email= inemail) AS srcMap INNER JOIN courses as crs on srcMap.courseId = crs.id;
	   END //
DELIMITER ;


DROP procedure IF EXISTS `applyForJob`;
DELIMITER //
CREATE PROCEDURE `applyForJob` (IN document LONGBLOB, IN applicationStatus VARCHAR(30), IN studentId INT(11), IN jobId INT(11))
BEGIN
INSERT INTO appliedJobs(document, applicationStatus, studentId, jobId) values (document, applicationStatus, studentId, jobId);
END//
DELIMITER ;

DROP procedure IF EXISTS `getAppliedJobList`;
DELIMITER //
CREATE PROCEDURE `getAppliedJobList`(IN studentId INT(11))
BEGIN
   select * from `CSCI5308_8_DEVINT`.`appliedJobs` aj inner join `CSCI5308_8_DEVINT`.`jobs` j on aj.id=j.id where aj.studentId=studentId;
END//

DELIMITER ;

DROP procedure IF EXISTS `getAllJobList`;
DELIMITER //
CREATE PROCEDURE `getAllJobList`()
BEGIN
select * from jobs j where j.jobStatus=1 and j.id IN(select distinct(jr.jobId) from jobRequirement jr left join studentRegisteredCourses src on jr.courseId=src.courseId where studentId=1);
END//

DELIMITER ;


DROP procedure IF EXISTS `fetchStudentDetails`;

DELIMITER //
CREATE PROCEDURE `fetchStudentDetails`(IN userSessionName varchar(50))
BEGIN
select * from `CSCI5308_8_DEVINT`.`students` where email=userSessionName;
END //

DELIMITER ;


DROP procedure IF EXISTS `withdrawApplication`;
DELIMITER //
CREATE PROCEDURE `withdrawApplication` (IN studentId INT(11), IN jobId INT(11))
BEGIN
delete from `CSCI5308_8_DEVINT`.`students` where studentId=studentId and jobId=jobId;
END //
DELIMITER ;



DROP procedure IF EXISTS `alreadyApplied`;
DELIMITER //
CREATE PROCEDURE `alreadyApplied` (IN studId INT(11), IN jId INT(11))
BEGIN
select count(*) from appliedJobs where studentId=studId and jobId=jId;
END//
DELIMITER ;