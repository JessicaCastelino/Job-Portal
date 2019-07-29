<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
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
<script>
		 $( function() {
    $( "#applicationDeadline" ).datepicker({dateFormat: 'yy-mm-dd'});
  } );
		$('document').ready(function () {

			var url = window.location.origin + "/getPrerequisiteCourses";
			fetch(url)
				.then(function (response) {
					return response.json();
				})
				.then(function (courseList) {
					console.log(JSON.stringify(courseList));
					createDynamicCheckbox(courseList);
				})



		})
		function createDynamicCheckbox(courseList) {
			document.getElementById('courseRequired').innerHTML = "";
			var selCourses = document.getElementById('hdnSelectedCourses').value;
			courseList.forEach(course => {
				var innerDiv = document.createElement("div");
				innerDiv.className = "coursesCheckbox";
				innerDiv.id = "coursesCheckbox";

				var coursecb = document.createElement("input");
				coursecb.type = "checkbox";
				//coursecb.id= "coursecb";
				coursecb.value = course.courseId;
				coursecb.checked = selCourses.indexOf(course.courseId) >= 0;
				document.getElementById("courseRequired").appendChild(innerDiv);
				innerDiv.appendChild(coursecb);
				innerDiv.appendChild(document.createTextNode(course.CourseName));
			});

		}
		function updateJobDetails() {

			var jobId = $('#hdnJobId').val();
			var jobTitle = $('#txtjobtitle').val();
			var location = $('#txtLocation').val();
			var noOfPosition = $('#numOfOpenPosition').val();
			var jobType = $('#selJobType').val();
			var rateOfPay = $('#txtRateofPay').val();
			var hourPerWeek = $('#txtHoursperweek').val();
			var  applicationDeadline = $('#applicationDeadline').val();
			var jobDescription = $('#txtJobDesc').val();
			var selectedCourseIds = fetchSelectedCourses();
			var data = { id: jobId, jobTitle: jobTitle, location: location, noOfPosition: noOfPosition, jobType: jobType, rateOfPay: rateOfPay, hourPerWeek: hourPerWeek, jobDescription: jobDescription,selectedCourseIds:selectedCourseIds, applicationDeadline:applicationDeadline }
			var url = window.location.origin + "/updateJobDetails";
			fetch(url, {
				method: 'PUT',
				body: JSON.stringify(data), // data can be `string` or {object}!
				headers: {
					'Content-Type': 'application/json'
				}
			}).then(res => res.json())
				.then(response => { console.log('Success:', JSON.stringify(response));
				window.location.href= window.location.origin + "/viewPostedJob?jobId=" + jobId
				})

				.catch(error => console.error('Error:', error));
		}
		function fetchSelectedCourses() 
		{
			var selCourses = document.getElementsByClassName('coursesCheckbox');
			var selCourseArray = [];
			for (var i = 0; i < selCourses.length; i++) {
				//console.log(selCourses[i].firstChild.checked);
				if (selCourses[i].firstChild.checked) {
					selCourseArray.push(selCourses[i].firstChild.value);
				}
			}
			return selCourseArray;
		}
	</script>
<style>
.coursescheckbox {
	margin-left: 26%;
}

.btnAlignment {
	margin-left: 46%;
	margin-top: 2%
}

.btnTobtnMargin {
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
		<br> <input type="hidden" id="hdnJobId" value="${jobDetails.id}" />
		<input type="hidden" id="hdnSelectedCourses"
			value="${jobDetails.selectedCourseIds}"> <br> <br>
		<div>
			<label class="col-sm-3">Job Title</label> <input type="text"
				id="txtjobtitle" value="${jobDetails.jobTitle}" /> <label
				class="col-sm-3">Location</label> <input type="text"
				id="txtLocation" value="${jobDetails.location}" />
		</div>
		<br>
		<div>
			<label class="col-sm-3">Open Positions</label> <input type="number"
				id="numOfOpenPosition" value="${jobDetails.noOfPosition}" /> <label
				class="col-sm-3">Job Type</label> <select name="jobType"
				id="selJobType">
				<c:forEach var="jobType" items="${jobTypes}">
					<option value="${jobType}"
						${jobType==jobDetails.jobType ? 'selected' : '' }>
						${jobType}</option>
				</c:forEach>
			</select>
		</div>
		<br>
		<div>
			<label class="col-sm-3">Rate of pay</label> <input type="text"
				id="txtRateofPay" value="${jobDetails.rateOfPay}" /> <label
				class="col-sm-3">Hours per week</label> <input type="text"
				id="txtHoursperweek" value="${jobDetails.hourPerWeek}" />
		</div>
		<div>
			<br> <label class="col-sm-3">Application deadline</label> <input
				type="text" id="applicationDeadline"
				value="${jobDetails.applicationDeadline}" /> <label class="col-sm-3">Job
				Description</label>
			<textarea row=3 id="txtJobDesc" value="${jobDetails.jobDescription}">${jobDetails.jobDescription} </textarea>
		</div>
		<br>
		<div>
			<label class="col-sm-3">Courses Required</label>
			<div id="courseRequired" class="coursescheckbox"></div>
		</div>
	</div>

	<div class="btnAlignment">
		<button id="btnUpdate" onclick="updateJobDetails()"
			class="btn btn-info">Update</button>
		<button id="btnCancel" class="btnTobtnMargin btn btn-info">
			Cancel</button>
	</div>

</body>

</html>