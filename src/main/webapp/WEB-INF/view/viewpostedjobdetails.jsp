<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
		<script>
		$('document').ready(function(){

	var url = window.location.origin + "/getPrerequisiteCourses";
	fetch(url)
	.then(function(response){
		return response.json();
	})
	.then(function(courseList){
		console.log(JSON.stringify(courseList));
		createDynamicCheckbox(courseList);
	}) 	



		})
		function createDynamicCheckbox (courseList)
{
document.getElementById('courseRequired').innerHTML="";
var selCourses = document.getElementById('hdnSelectedCourses').value;
courseList.forEach(course => {
	var innerDiv = document.createElement("div");
	innerDiv.className="coursesCheckbox";
	innerDiv.id= "coursesCheckbox";

	var coursecb = document.createElement("input");
	coursecb.type="checkbox";
	//coursecb.id= "coursecb";
	coursecb.value = course.courseId;
	coursecb.checked = selCourses.indexOf(course.courseId) >= 0;
	coursecb.disabled = true;
	document.getElementById("courseRequired").appendChild(innerDiv);
	innerDiv.appendChild(coursecb);
	innerDiv.appendChild(document.createTextNode(course.CourseName));	
	});

}
function editDetails()
{
	var jobId = document.getElementById('hdnjobId').value;
	window.location.href = window.location.origin + "/editPostedJob?jobId=" + jobId;
}
</script>
<style>
.coursescheckbox {
	margin-left: 18%;
}
.btnAlignment
{
	margin-left: 46%;
	margin-top: 2%
}
.btnTobtnMargin
{
	margin-left: 2%
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

<div>
	<input type="hidden" id="hdnjobId" value="${jobDetails.id}"/>
	<input type="hidden" id="hdnSelectedCourses" value="${jobDetails.selectedCourseIds}">
					<div>
					
					<table border="1" class="table table-hover">
						<tr>
						<th>Job Title</th>
						<td id="lbljobtitle">${jobDetails.jobTitle}</td>
						</tr>
						
						<tr>
						<th>Location</th>
						<td id="lblLocation">${jobDetails.location}</td>
						</tr>
						
						<tr>
						<th>Open Positions</th>
						<td id="numOfOpenPosition">${jobDetails.hourPerWeek}</td>
						</tr>
						
						<tr>
						<th>Job Type</th>
						<td id="lblJobType">${jobDetails.jobType}</td>
						</tr>
						
						<tr>
						<th>Rate of pay</th>
						<td id="txtRateofPay">${jobDetails.rateOfPay}</td>
						</tr>
						
						<tr>
						<th>Hours per week</th>
						<td id="txtHoursperweek">${jobDetails.hourPerWeek}</td>
						</tr>
						
						<tr>
						<th>Application deadline</th>
						<td id="applicationDeadline">${jobDetails.applicationDeadline}</td>
						</tr>
						
						<tr>
						<th>Job Description</th>
						<td id="txtJobDesc">${jobDetails.jobDescription}</td>
						</tr>
						
						<tr>
						<th>Courses Required</th>
						<td id="courseRequired" class="coursescheckbox"></td>
						</tr>
						</table>
						</div>
	
	
	
</div>

<div class="btnAlignment">
<button id="btnEdit" class="btn btn-info" onclick="editDetails()"> Edit Details</button>

</div>
</body>
</html>