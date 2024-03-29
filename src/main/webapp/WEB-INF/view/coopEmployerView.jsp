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
					<a class="navbar-brand" href="/logout">Logout</a>
				</div>

			</div>
		</nav>
	</div>
	<div class="container">
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item"><a class="nav-link active" id="home-tab"
				data-toggle="tab" href="#home" role="tab" aria-controls="home"
				aria-selected="true">Pending employer requests</a></li>
			<li class="nav-item"><a class="nav-link" id="profile-tab"
				data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
				aria-selected="false">Active employers</a></li>
		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="home" role="tabpanel"
				aria-labelledby="home-tab">
				<div>
					<br />
					<table border="1" class="table table-hover">
						<tr class="table-info">
							<th>Recruiter Name</th>
							<th>Email ID</th>
							<th>Organization</th>
							<th>Action</th>
						</tr>
						<c:forEach items="${recruiterRequests}" var="request">
							<tr>
								<td>${request.firstname}</td>
								<td>${request.email}</td>
								<td>${request.companyname}</td>
								<td>
									<!-- View button -->
									<div class="form-input">
									<a class="btn btn-info btn-block my-4"
											href="/approveReject?id=${request.id}">Approve</a>
									</div> <!-- Apply button -->
									<div class="form-input">
										<a class="btn btn-info btn-block my-4"
											href="/approveReject?id=${request.id}">Reject</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="tab-pane fade" id="profile" role="tabpanel"
				aria-labelledby="profile-tab">

				<div>
					<br />
					<table border="1" class="table table-hover">
						<tr class="table-info">
							<th>Job ID</th>
							<th>Position</th>
							<th>Organization</th>
							<th>Location</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
						
					</table>
				</div>

			</div>
		</div>
	</div>

</body>
</html>