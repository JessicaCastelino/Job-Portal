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
<body>
	<div class="home-heading">
		<h1>Welcome to My Career</h1>
		${name}
	</div>
	<div class="container">
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item"><a class="nav-link active" id="home-tab"
				data-toggle="tab" href="#home" role="tab" aria-controls="home"
				aria-selected="true">SIGN IN</a></li>
			<li class="nav-item"><a class="nav-link" id="profile-tab"
				data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
				aria-selected="false">SIGNUP FOR EMPLOYER</a></li>
		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="home" role="tabpanel"
				aria-labelledby="home-tab">
				<!-- Default form login -->
				<form class="text-center border border-light p-5" action = "/login" method = "POST" modelAttribute = "userLogin">

					<p class="h4 mb-4">Sign In</p>
					<div class="form-field">
						<div class="form-input">
							<select class="form-control mb-4" id="exampleFormControlSelect1" name="role">
								<option>Student</option>
								<option>Employer</option>
								<option>Co-op Admin</option>
							</select>
						</div>

						<!-- Email -->
						<div class="form-input">
							<input type="email" id="defaultLoginFormEmail"
								class="form-control mb-4" placeholder="E-mail" name="username">
						</div>

						<!-- Password -->
						<div class="form-input">
							<input type="password" id="defaultLoginFormPassword"
								class="form-control mb-4" placeholder="Password" name="password">
						</div>
						<div class="d-flex justify-content-around"></div>

						<!-- Sign in button -->
						<div class="form-input">
							<button class="btn btn-info btn-block my-4" type="submit">Sign
								in</button>
						</div>
					</div>
				</form>
				<!-- Default form login -->
			</div>
			<div class="tab-pane fade" id="profile" role="tabpanel"
				aria-labelledby="profile-tab">

				<!-- Default form Sign Up -->
				<form class="text-center border border-light p-5">

					<p class="h4 mb-4">Sign Up</p>
					<div class="form-field">
						<div class="user-info">
							<!-- Salutation -->
							<div class="user-select">
								<select class="form-control mb-4" id="salutation">
									<option>Mr.</option>
									<option>Mrs.</option>
									<option>Ms.</option>
								</select>
							</div>
							<!-- First Name -->
							<div class="user-select">
								<input type="text" id="fname" class="form-control mb-4"
									placeholder="First Name">
							</div>
							<!-- Last Name -->
							<div class="user-select">
								<input type="text" id="lname" class="form-control mb-4"
									placeholder="Last Name">
							</div>

						</div>

						<!-- Email -->
						<div class="form-input">
							<input type="email" id="emp-email" class="form-control mb-4"
								placeholder="E-mail">
						</div>

						<!-- Company -->
						<div class= "form-input">
							<select class="form-control mb-4" id="company">
								<option>IBM</option>
								<option>Deloitte</option>
								<option>RBC</option>
							</select>
						</div>
						<!-- Designation -->
						<div class= "form-input">
							<input type="text" id="fname" class="form-control mb-4"
								placeholder="Designation">
						</div>

						<!-- Address -->
						<div class="form-input">
							<textarea id="address" class="form-control mb-4"
								placeholder="Address"></textarea>
						</div>

						<!-- Sign up button -->
						<div class="form-input">
							<button class="btn btn-info btn-block my-4" type="submit">Sign
								Up</button>
						</div>
					</div>
				</form>
				<!-- Default form Sign Up -->

			</div>
		</div>
	</div>

</body>
</html>