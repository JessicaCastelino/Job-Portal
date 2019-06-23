<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Career Home</title>

<link href="css/homepage.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmoM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

</head>
<style>

	.proflist{
		display:block;
	}
	.cprof{
		display: inline-block;
		margin: 2%;
	}
	.jumbotron{
		text-align: center;
	}


</style>
<body>
	<div>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/homepage">My Career</a>
				</div>
			</div>
		</nav>
	</div>

	<div class="jumbotron">
		<h1 class="display-4">Your Profile !</h1>
		<div class="proflist">
			<div class="cprof">
				<span><p class="lead"><b>Name:</b> ${profileData.firstname} ${profileData.lastname}</p></span>
			</div>
			<div class="cprof">
				<span><p class="lead"><b>Banner Id:</b> ${profileData.bannerid}</p></span>
			</div>
		</div>
		<div class="proflist">
			<div class="cprof">
				<span><p class="lead"><b>Email:</b> ${profileData.email}</p></span>
			</div>
			<div class="cprof">
				<span><p class="lead"><b>Phone:</b> ${profileData.phonenumber}</p></span>
			</div>
		</div>
		<div class="proflist">
			<div class="cprof">
				<span><p class="lead"><b>Degree:</b> ${profileData.degree}</p></span>
			</div>
			<div class="cprof">
				<span><p class="lead"><b>Department:</b> ${profileData.department}</p></span>
			</div>
		</div>
		<div>
			<a class="btn btn-primary btn-lg" href="/homepage" role="button">My
				DashBoard</a>
		</div>

	</div>

</body>
</html>