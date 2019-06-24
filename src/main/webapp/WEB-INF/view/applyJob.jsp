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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
	<div class="home-heading">
		<h1>Apply for Job</h1>
	</div>
	<div class="container">

		<form class="text-center border border-dark p-5" method="POST" enctype="multipart/form-data" action="/upload?jobId=${jobId}">

					<p class="h1 form-group">Documents</p>
					<p class="h4 mb-4 form-group">Please upload your cover letter, resume and transcripts as a single PDF.</p>
					<div class="form-field">
						<input type="file" name="file" />

						
						<div class="d-flex justify-content-around form-group"></div>

			             <div class="form-input form-group	">
							<button class="btn btn-info btn-block my-4" type="submit">Submit application</button>
			             </div>
			             <div class="form-input form-group">
							<button class="btn btn-info btn-block my-4" type="submit" onclick="window.close()">Close</button>
						 </div>
					</div>
				</form>
			
			
		</div>
	

</body>
</html>