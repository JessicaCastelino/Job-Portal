
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Active Jobs</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
table {
	border-collapse: collapse;
	width: 100%;
	border: 1px solid #ddd;
}

.tablehdr {
	background-color: midnightblue;
	color: white;
}

table th, table td {
	text-align: left;
	padding: 12px;
}

/* .closeJobBtn, .viewApplicantsBtn, {
	background-color: #17a2b8;
	height: 60px;
	color: white;
	width: 100px;
} */

.coursescheckbox {
	margin-left: 26%;
}
.errorMsgColor
{
	color: red;
}
</style>
</head>
<body>

	<div>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/homepage">My Career</a>
				</div>
				<div class="navbar-header">
					<a class="navbar-brand" href="/logout">Logout</a>
				</div>
			</div>
		</nav>
	</div>

	<button id="btnAddJob" data-toggle="modal" data-target="#modalpopup"
		class="buttonmargin btn btn-info" onclick="loadPrerequisiteCourse()">Add
		Job</button>
	<button id="btnAddJob" class="buttonmargin btn btn-info" onclick="window.location.href = window.location.origin + '/closedjobs'">View Closed Jobs</button>
	<div class="modal fade" id="modalpopup" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Create new Job</h4>
					<button type="button" class="class btn btn-info" data-dismiss="modal">&times;</button>

				</div>
				<div class="modal-body">
					<div>
						<label class="col-sm-3">Job Title</label> <input type="text"
							id="txtjobtitle" /> <label class="col-sm-3">Location</label> <input
							type="text" id="txtLocation" />
					</div>
					<br>
					<div>
						<label class="col-sm-3">Open Positions</label> <input
							type="number" id="numOfOpenPosition" /> <label class="col-sm-3">Job
							Type</label> 
						<select name="jobType" id="selJobType">
							<c:forEach var="jobType" items="${jobTypes}">
								<option value="${jobType}">${jobType}</option>
							</c:forEach>
						</select>
					</div>
					<br>
					<div>
						<label class="col-sm-3">Rate of pay</label> <input type="text"
							id="txtRateofPay" /> <label class="col-sm-3">Hours per
							week</label> <input type="text" id="txtHoursperweek" />
					</div>
					<div>
						<br> <label class="col-sm-3">Application deadline</label> <input
							type="text" id="applicationDeadline" /> <label class="col-sm-3">Job
							Description</label>
						<textarea row=3 id="txtJobDesc"> </textarea>
					</div>
					<br>
					<div>
						<label class="col-sm-3">Courses Required</label>
						<div id="courseRequired" class="coursescheckbox"></div>
					</div>
					<br>
					<div id="errorMsgs">
						<span id="errMsg"></span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn btn-info" onclick="saveJob()">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<table id="activeJobs" class="table table-hover">
		<thead>
			<tr class="table-info">
				<th style="display: none;">ID</th>
				<th style="width: 6%;">Job Id</th>
				<th>Title</th>
				<th>Job Type</th>
				<th>Company</th>
				<th>Location</th>
				<th>Deadline</th>
				<th>Prerequisite Courses</th>
				<th style="width: 75px"></th>
				<th style="width: 75px"></th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>

	<script>
  // Add job code starts
  $( function() {
    $( "#applicationDeadline" ).datepicker({dateFormat: 'yy-mm-dd'});
  } );
function loadPrerequisiteCourse ()
{
	var url = window.location.origin + "/getPrerequisiteCourses";
	fetch(url)
	.then(function(response){
		return response.json();
	})
	.then(function(courseList){
		console.log(JSON.stringify(courseList));
		createDynamicCheckbox(courseList);
	}) 	
}
function createDynamicCheckbox (courseList)
{
document.getElementById('courseRequired').innerHTML="";
courseList.forEach(course => {
	var innerDiv = document.createElement("div");
	innerDiv.className="coursesCheckbox";
	innerDiv.id= "coursesCheckbox";

	var coursecb = document.createElement("input");
	coursecb.type="checkbox";
	//coursecb.id= "coursecb";
	coursecb.value = course.courseId;
	document.getElementById("courseRequired").appendChild(innerDiv);
	innerDiv.appendChild(coursecb);
	innerDiv.appendChild(document.createTextNode(course.CourseName));	
	});

}
function fetchSelectedCourses ()
{
var selCourses = document.getElementsByClassName('coursesCheckbox');
var selCourseArray = [];
for (var i=0; i<selCourses.length;i++)
{
//console.log(selCourses[i].firstChild.checked);
if (selCourses[i].firstChild.checked)
{
	 selCourseArray.push(selCourses[i].firstChild.value);
}
}
return selCourseArray;	
}
function validateRegistration()
{
	isValidForm = true;
	$("#errorMsgs span").empty();
	var selectedCourses = fetchSelectedCourses();
	if($('#txtjobtitle').val() == "")
	{
		$("#errorMsgs span").append("* jobTitle is mandatory <br>").addClass("errorMsgColor");
		isValidForm = false;
	}
	if($('#txtLocation').val() == "")
	{
		$("#errorMsgs span").append("* Location is mandatory <br>").addClass("errorMsgColor");
		isValidForm = false;
	}
	if($('#applicationDeadline').val() == "")
	{
		$("#errorMsgs span").append("* Application deadline is mandatory <br>").addClass("errorMsgColor");
		isValidForm = false;
	}
	if($('#numOfOpenPosition').val() == "")
	{
		$("#errorMsgs span").append("* Number of position is mandatory <br>").addClass("errorMsgColor");
		isValidForm = false;
	}
	if(selectedCourses.length == 0)
	{
		$("#errorMsgs span").append("* Prerequisite courses is mandatory").addClass("errorMsgColor");
		isValidForm = false;
	}
	return isValidForm;
}
function saveJob()
{
	 if(validateRegistration())
	 {
	var  jobTitle = $('#txtjobtitle').val();
	var  location = $('#txtLocation').val();
	var  noOfPosition = $('#numOfOpenPosition').val();
	var  jobType = $('#selJobType').val();
	var  rateOfPay = $('#txtRateofPay').val();
	var  hourPerWeek = $('#txtHoursperweek').val();
	var  applicationDeadline = $('#applicationDeadline').val();
	var  jobDescription = $('#txtJobDesc').val();
	var selectedCourseIds = fetchSelectedCourses();
	var data = {jobTitle : jobTitle,location:location,noOfPosition:noOfPosition,jobType:jobType,rateOfPay:rateOfPay,hourPerWeek:hourPerWeek,jobDescription:jobDescription,selectedCourseIds:selectedCourseIds, applicationDeadline:applicationDeadline }
	var url = window.location.origin + "/saveJob";
	fetch(url, {
		  method: 'POST', // or 'PUT'
		  body: JSON.stringify(data), // data can be `string` or {object}!
		  headers:{
		    'Content-Type': 'application/json'
		  }
		}).then(res => res.json())
		.then(response => console.log('Success:', JSON.stringify(response)))
		.catch(error => console.error('Error:', error));
		$("#modalpopup").hide();
		window.location.reload(true);
	 }
}
// Add job code ends
	  const http = new XMLHttpRequest();
      var baseUrl = window.location.origin;
      var url = baseUrl + '/activejobs';
	  http.open('GET', url, true);
	  http.send();
      http.onreadystatechange = function () 
      {
        if (this.readyState === 4 && this.status === 200) 
        {
	      var jobs = JSON.parse(http.response);
          jobs.forEach(job => 
          {
	        var activeJobsTable = document.getElementById('activeJobs');
	        var row = activeJobs.insertRow();
	        var colId = row.insertCell(0);
	        colId.style.display = 'none';
	        var colJobId = row.insertCell(1);
	        var colJobTitle = row.insertCell(2);
	        var colJobType = row.insertCell(3);
            var colOrg = row.insertCell(4);
            var colLocation = row.insertCell(5);
            var colAppDeadline = row.insertCell(6);
            var colPrereqCourses = row.insertCell(7);
	        var colCloseJob = row.insertCell(8);
	        var colViewApplicants = row.insertCell(9);
	        colId.innerText = job.id;
	        colJobId.innerText = job.id;
          	colJobTitle.innerHTML = '<a href="' + window.location.origin + '/viewPostedJob?jobId=' + job.id + '"">' + job.jobTitle + '</a>';
	        colJobType.innerText  = job.jobType;
            colOrg.innerText = job.organization;
            colLocation.innerText = job.location;
			colPrereqCourses.innerText = job.requiredCourses;
            colAppDeadline.innerText = job.applicationDeadline;
	        colCloseJob.innerHTML = '<button class="closeJobBtn btn btn-info" onclick="closeJob(this)">Close Job</button>';
	        colViewApplicants.innerHTML = '<button class="viewApplicantsBtn btn btn-info" onclick="viewApplicants(this)">View Applicants</button>';
	      });
	    }
	  };
      http.onerror = function () 
      {
	    console.log(http.response);
      };
      
      function viewApplicants(srcElement) 
      {
		var currentRowIndex = srcElement.closest('tr').rowIndex;
        var activeJobsTable = document.getElementById('activeJobs');
        var id = activeJobsTable.rows[currentRowIndex].cells[0].innerText;
      	var url = baseUrl + '/applications?jobId=' + id;
		window.location.href = url;
      }

      function closeJob(srcElement) 
      {
        const httpReq = new XMLHttpRequest();
        var currentRowIndex = srcElement.closest('tr').rowIndex;
        var activeJobsTable = document.getElementById('activeJobs');
        var id = activeJobsTable.rows[currentRowIndex].cells[0].innerText;
        var params = {id : 1};
        
        httpReq.open('PUT', baseUrl + '/closeJob', true);
        httpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        httpReq.send("id=" + id);
        httpReq.onreadystatechange = function () 
        {
            if (this.readyState === 4 && this.status === 200) 
            {
                if(httpReq.responseText == "true")
                {
                    activeJobsTable.deleteRow(currentRowIndex);
                }
            }
        };
        httpReq.onerror = function () 
        {
            console.log(http.response);
        };
      }
</script>
</body>
</html>