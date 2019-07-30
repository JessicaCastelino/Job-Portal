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
IF (criteria = 'all') THEN
    select * from jobs j where j.jobStatus=1;
ELSE
    select * from jobs j where j.jobStatus=1 and j.id IN(select distinct(jr.jobId) from jobRequirement jr left join studentRegisteredCourses src on jr.courseId=src.courseId where studentId=studId);
END IF;
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
delete from `CSCI5308_8_DEVINT`.`appliedJobs` where id=jId;
END //
DELIMITER ;



DROP procedure IF EXISTS `alreadyApplied`;
DELIMITER //
CREATE PROCEDURE `alreadyApplied` (IN studId INT(11), IN jId INT(11))
BEGIN
select count(*) from appliedJobs where studentId=studId and jobId=jId;
END//
DELIMITER ;

DROP procedure IF EXISTS `applyForJob`;
DELIMITER $$
CREATE PROCEDURE `applyForJob`(IN document LONGBLOB, IN applicationStatus VARCHAR(30), IN studentId INT(11), IN jobId INT(11))
BEGIN
INSERT INTO appliedJobs(document, applicationStatus, studentId, jobId) values (document, applicationStatus, studentId, jobId);
END$$
DELIMITER ;

DROP procedure IF EXISTS `employerRequest`;
DELIMITER $$
CREATE PROCEDURE `employerRequest`(IN etitle varchar(5), IN efn varchar(50), IN eln varchar(50), IN eemail varchar(50), IN desig varchar(50), IN company varchar(50), IN isAct tinyint(1))
BEGIN
   INSERT into employers(title,firstname,lastname,email,designation,companyname,isActive) values(etitle,efn,eln,eemail,desig,company,isAct);
   END$$
DELIMITER ;

DROP procedure IF EXISTS `fetchStudentDetails`;
DELIMITER $$
CREATE PROCEDURE `fetchStudentDetails`(IN userSessionName varchar(50))
BEGIN
select * from `students` where email=userSessionName;
END$$
DELIMITER ;

DROP procedure IF EXISTS `getActiveJobsForEmployer`;
DELIMITER $$
CREATE PROCEDURE `getActiveJobsForEmployer`(employerUserName varchar(50))
BEGIN
	select j.id, j.jobTitle, j.jobType, j.location, emp.companyname as organization, j.applicationDeadline, getRequiredCoursesForJob(j.id) as requiredCourses from jobs j
    join employers emp
    on j.employerid = emp.id
    where emp.email = employerUserName and
    j.jobStatus = 1;
END$$
DELIMITER ;

DROP procedure IF EXISTS `getAllJobList`;
DELIMITER $$
CREATE PROCEDURE `getAllJobList`(IN studId INT(11))
BEGIN
select * from jobs j where j.jobStatus=1 and j.id IN(select distinct(jr.jobId) from jobRequirement jr left join studentRegisteredCourses src on jr.courseId=src.courseId where studentId=studId);
END$$
DELIMITER ;

DROP procedure IF EXISTS `getAppliedJobList`;
DELIMITER $$
CREATE PROCEDURE `getAppliedJobList`(IN studentId INT(11))
BEGIN
   select * from `appliedJobs` aj inner join `jobs` j on aj.id=j.id where aj.studentId=studentId;
END$$
DELIMITER ;

DROP procedure IF EXISTS `getClosedJobsForEmployer`;
DELIMITER $$
CREATE PROCEDURE `getClosedJobsForEmployer`(employerUserName varchar(50))
BEGIN
	select j.id, j.jobTitle, j.jobType, j.location, emp.companyname as organization, j.applicationDeadline, getRequiredCoursesForJob(j.id) as requiredCourses from jobs j
    join employers emp
    on j.employerid = emp.id
    where emp.email = employerUserName and 
    (j.jobStatus = 0 or j.jobStatus IS NULL);
END$$
DELIMITER ;

DROP procedure IF EXISTS `getJobsForEmployerByStatus`;
DELIMITER $$
CREATE PROCEDURE `getJobsForEmployerByStatus`(employerid int, active boolean)
BEGIN
	select id, jobId, jobTitle, jobType, location, organization, applicationDeadline from jobs where jobStatus IS NOT NULL AND jobStatus = active;
END$$
DELIMITER ;

DROP procedure IF EXISTS `getPostedJobDetails`;
DELIMITER $$
CREATE PROCEDURE `getPostedJobDetails`(jobId int)
BEGIN
select *,getCompletedCoursesIds (jobId) as prerequisitecourses from jobs where id = jobId;
END$$
DELIMITER ;

DROP procedure IF EXISTS `insertjobRequirementRecord`;
DELIMITER $$
CREATE PROCEDURE `insertjobRequirementRecord`(
courseIds varchar(50),
jobRecordId int
)
BEGIN
declare courselist varchar(50);
declare courseid varchar(2);
declare output varchar(50);
set courselist = courseIds;
set output = '';
delete from jobRequirement where jobId=jobRecordId;
while concat(',', courselist) != output do
select substring_index(courseIds, ',', 1) into courseid;
set courseIds = replace(courseIds, concat(substring_index(courseIds, ',', 1), ','), '');
set output = concat(output, ',');
set output = concat(output, courseid);
-- select courseid;
insert into jobRequirement (courseid, jobid)
select courseid,jobRecordId;

end while;
END$$
DELIMITER ;

DROP procedure IF EXISTS `sp_closeactivejob`;
DELIMITER $$
CREATE PROCEDURE `sp_closeactivejob`(jobRecordId int)
BEGIN
UPDATE jobs
SET
jobStatus = 0
WHERE id = jobRecordId;
END$$
DELIMITER ;

DROP procedure IF EXISTS `sp_getjobapplications`;
DELIMITER $$
CREATE PROCEDURE `sp_getjobapplications`(jobRecordId int)
BEGIN
SELECT application.id,
	stdnt.firstname, 
    stdnt.lastname,
    stdnt.email,
    document,
    applicationStatus,
    getCompletedCoursesForStudent(studentId) as completedCourses,
    stdnt.bannerid as studentId,
    jobId 
    FROM appliedJobs application
    JOIN students stdnt
    ON application.studentId = stdnt.id
    where jobId = jobRecordId;
END$$
DELIMITER ;

DROP procedure IF EXISTS `sp_getPrerequisiteCourses`;
DELIMITER $$
CREATE PROCEDURE `sp_getPrerequisiteCourses`()
BEGIN
select id as courseId, courseName as courseName from courses;
END$$
DELIMITER ;

DROP procedure IF EXISTS `sp_insertjobdetails`;
DELIMITER $$
CREATE PROCEDURE `sp_insertjobdetails`(jobTitle varchar(50),
jobLocation varchar(50),
noOfPosition int,
rateOfPay int,
hourPerWeek int,
jobDescription varchar(255),
jobType varchar(50),
applicationDeadline date,
emailId varchar(50),
out jobId int )
BEGIN
Declare empId int;
select id into empId from employers where email = emailid;
INSERT INTO jobs (`jobTitle`, `location`, `openPosition`, `jobType`, `rateOfPay`, `hoursPerWeek`, `applicationDeadline`,`jobDescription`,`employerid`,`jobStatus`) 
VALUES (jobTitle, jobLocation, noOfPosition, jobType, rateOfPay, hourPerWeek,applicationDeadline, jobDescription, empId,1);
select last_insert_id() into jobId;
END$$
DELIMITER ;

DROP procedure IF EXISTS `sp_insertjobrequirement`;
DELIMITER $$
CREATE PROCEDURE `sp_insertjobrequirement`(
jobRecordId int,
preReqCourseId int
)
BEGIN
Declare existingrecord int;
select count(*) into existingrecord from jobRequirement  where jobId = jobRecordId and courseId=preReqCourseId;
if (existingrecord < 1)
then
insert into jobRequirement 
(
`jobId`,
`courseId`)
VALUES
(jobRecordId,
preReqCourseId);
else
delete from jobRequirement  where jobId = jobRecordId;
insert into jobRequirement 
(
`jobId`,
`courseId`)
VALUES
(jobRecordId,
preReqCourseId);
END if;
END$$
DELIMITER ;

DROP procedure IF EXISTS `sp_updateApplicationStatus`;
DELIMITER $$
CREATE PROCEDURE `sp_updateApplicationStatus`(applicationId int, applicationStatus varchar(30))
BEGIN
UPDATE appliedJobs
SET
`applicationStatus` = applicationStatus
WHERE `id` = applicationId;

END$$
DELIMITER ;

DROP procedure IF EXISTS `sp_updateJobStatus`;
DELIMITER $$
CREATE PROCEDURE `sp_updateJobStatus`(jobRecordId int, status bit)
BEGIN
UPDATE jobs
SET
jobStatus = status
WHERE id = jobRecordId;
END$$
DELIMITER ;

DROP procedure IF EXISTS `updatejobdetails`;
DELIMITER $$
CREATE PROCEDURE `updatejobdetails`(
jobId int,
jobTitle varchar (50),
location varchar (50),
jobType varchar (50),
noOfPosition int,
rateOfPay int,
hourPerWeek int,
applicationDeadline date,
jobDescription varchar (255)
)
BEGIN
UPDATE `jobs`
SET
`jobTitle` = jobTitle,
`location` = location,
`openPosition` = noOfPosition,
`jobType` = jobType,
`rateOfPay` = rateOfPay,
`hoursPerWeek` = hourPerWeek,
`applicationDeadline` = applicationDeadline,
`jobDescription` = jobDescription
WHERE `id` =jobId;

END$$
DELIMITER ;


DROP procedure IF EXISTS `fetchRecruiterRequests`;
DELIMITER $$

CREATE PROCEDURE `fetchRecruiterRequests` ()
BEGIN
SELECT id, firstname, lastname, email, companyname FROM employers where isActive=0;
END$$

DELIMITER ;

DROP procedure IF EXISTS `makeEmployerActive`;
DELIMITER $$
CREATE PROCEDURE `makeEmployerActive` (IN reqID INT(11), IN name VARCHAR(50), IN password VARCHAR(100))
BEGIN
UPDATE employers SET isActive = 1 WHERE id=reqID;
INSERT INTO userlogin (userName,pwd,role) VALUES (name, password, 'employer');
UPDATE employers SET userid = (SELECT id from userlogin where userName=name) WHERE id=reqID;
END$$

DELIMITER ;


DROP procedure IF EXISTS `rejectEmployer`;
DELIMITER $$
CREATE PROCEDURE `rejectEmployer` (IN reqID INT(11))
BEGIN
DELETE FROM employers where id=reqID;
END$$
DELIMITER ;


DROP procedure IF EXISTS `fetchRecruiter`;

DELIMITER $$
CREATE PROCEDURE `fetchRecruiter` (IN reqID INT(11))
BEGIN
SELECT id, firstname, lastname, email, companyname FROM employers where id=reqID;
END$$

DELIMITER ;

DROP procedure IF EXISTS `checkDupicateStudent`;
DELIMITER $$
CREATE  PROCEDURE `checkDupicateStudent`(bnrId varchar(50))
BEGIN
SELECT bannerId FROM students where bannerid = bnrId;
END$$
DELIMITER ;
