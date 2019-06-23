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
<div>
	<br>
	<br>
	<input type="hidden" id="hdnjobId" value="${jobDetails.id}"/>
	<br>
	<div>
		<label class="col-sm-2">Job Title</label>
		<label id="lbljobtitle" class="col-sm-3">${jobDetails.jobTitle}</label>
		<label class="col-sm-2">Location</label> 
		<label id="lblLocation" class="col-sm-3" >${jobDetails.location}</label>
	</div>
	<br>
	<div>
		<label class="col-sm-2">Open Positions</label>
		<label id="numOfOpenPosition" class="col-sm-3">${jobDetails.hourPerWeek}</label>
		<label class="col-sm-2">Job Type</label> 
		<label id="lblJobType" class="col-sm-3" >${jobDetails.location}</label>
	</div>
	<br>
	<div>
		<label class="col-sm-2">Rate of pay</label> 
		<label id="txtRateofPay" class="col-sm-3">${jobDetails.rateOfPay}</label>
		<label class="col-sm-2">Hours per week</label> 
		<label id="txtHoursperweek" class="col-sm-3">${jobDetails.hourPerWeek}</label>
	</div>
	<div>
		<br>
		<label class="col-sm-2">Application deadline</label> 
		<label id="applicationDeadline" class="col-sm-3">Hi</label> 
		<label class="col-sm-2">Job Description</label>
		<label row=3 id="txtJobDesc" class="col-sm-3" >${jobDetails.jobDescription} </label>
	</div>
	<br>
	<div>
		<label class="col-sm-2">Courses Required</label>
		<div id="courseRequired" class="coursescheckbox"></div>
	</div>
</div>

<div class="btnAlignment">
<button id="btnEdit" onclick="editDetails()"> Edit Details</button>

</div>
</html>