<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Career</title>

<link href="css/homepage.css" rel="stylesheet">
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<div>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/homepage">My Career</a>
				</div>
				<div class="navbar-header">
					<a class="navbar-brand" href="/profile">Profile</a>
				</div>
				<div class="navbar-header">
					<a class="navbar-brand" href="/logout">Logout</a>
				</div>

			</div>
		</nav>
	</div>
	<div class="home-heading">
		<h1>Job Details</h1>
	</div>
	<div class="container">

		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="home" role="tabpanel"
				aria-labelledby="home-tab">
				<div>
					<br />
					<table border="1" class="table table-hover">
						<tr>
							<th>Job ID</th>
							<td>${job.id}</td>
						</tr>
						<tr>
							<th>Title</th>
							<td>${job.jobTitle}</td>
						</tr>
						<tr>
							<th>Organization</th>
							<td>${job.organization}</td>
						</tr>
						<tr>
							<th>Location</th>
							<td>${job.location}</td>
						</tr>
						<tr>
							<th>Job Type</th>
							<td>${job.jobType}</td>
						</tr>
						<tr>
							<th>Term</th>
							<td>${job.jobType}</td>
						</tr>
						<tr>
							<th>Open Position</th>
							<td>${job.noOfPosition}</td>
						</tr>
						<tr>
							<th>Hourly Wage</th>
							<td>${job.rateOfPay}</td>
						</tr>
						<tr>
							<th>Hours per Week</th>
							<td>${job.hourPerWeek}</td>
						</tr>
						<tr>
							<th>Job Description</th>
							<td>${job.jobDescription}</td>
						</tr>
						<tr>
							<th>Application Deadline</th>
							<td>${job.applicationDeadline}</td>
						</tr>
					</table>
					<div class="form-input">
						<button class="btn btn-info btn-block my-4" type="submit"
							value="${job.id}"
							onclick="window.open('/applyJob','Apply for job', 'width=500,height=500')">Apply</button>
					</div>
				</div>
			</div>


		</div>
	</div>

</body>
</html>