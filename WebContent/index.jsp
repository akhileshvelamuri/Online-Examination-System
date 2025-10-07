<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>

	<nav
		class="navbar navbar-expand-lg mb-4 top-bar navbar-static-top sps sps--abv">
	<div class="navbar-header">
		<a class="navbar-brand mx-auto" href="index.jsp">Online<span>
				Examination System</span></a>
	</div>
	</nav>


	<div class="card bg-primary">
		<div class="card-header text-center">For all Candidates</div>
		<div class="card-body text-center">

			<!--Candidate Login Modal-->
			<div class="container">
				<button type="button" class="btn" data-toggle="modal"
					data-target="#myModal">Candidate Login</button>
				<div class="modal" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Candidate Login</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<br>
							</div>
							<div class="modal-body text-left">
								<form name="Login" action="OESController"
									onsubmit="return LoginValidate()" method="post">

									<div class="form-group">
										<label for="email">Email:</label> <input type="email"
											class="form-control" id="email" name="email" required>
									</div>
									<div class="form-group">
										<label for="password">Password:</label> <input type="password"
											class="form-control" id="password" name="password" required>
									</div>
									<br>
									<button type="submit" name="action" value="Candidate Login"
										class="btn btn-primary" align="center">Candidate
										Login</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="card bg-warning">
		<div class="card-header text-center">For all Admins</div>
		<div class="card-body text-center">
			<!--Admin Login Modal-->
			<div class="container">
				<button type="button" class="btn" data-toggle="modal"
					data-target="#myModal2">Admin Login</button>
				<div class="modal" id="myModal2">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Admin Login</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<br>
							</div>
							<div class="modal-body text-left">
								<form name="Login" action="OESController"
									 method="post">
									<label name = "err">${requestScope.err}</label>
									<div class="form-group">
										<label for="email">Email:</label> <input type="email"
											class="form-control" id="email" name="email" required>
									</div>
									<div class="form-group">
										<label for="password">Password:</label> <input type="password"
											class="form-control" id="password" name="password" required>
									</div>

									<br>
									<button type="submit" name="action" value="Admin Login"
										class="btn btn-primary" align="center">Admin Login</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="card bg-success">
		<div class="card-header text-center">Registration</div>
		<div class="card-body text-center">
			<!-------Sign Up Modal-------->
			<div class="container">
				<button type="button" class="btn" data-toggle="modal"
					data-target="#upModal">Sign Up</button>
				<div class="modal" id="upModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Sign Up</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<br>
							</div>
							<div class="modal-body text-left">
								<form name="RegForm" action="OESController"
									onsubmit="return RegValidate()" method="post">
									<div class="form-group">
										<label for="first_name">First Name:</label> <input type="text"
											class="form-control" id="first_name" name="first_name"
											required>
									</div>
									<div class="form-group">
										<label for="last_name">Last Name:</label> <input type="text"
											class="form-control" id="last_name" name="last_name" required>
									</div>
									<div class="form-group">
										<label for="qualification">Qualification:</label> <input
											type="text" class="form-control" id="qualification"
											name="qualification" required>
									</div>
									<div class="form-group">
										<label for="email">Email:</label> <input type="email"
											class="form-control" id="email" name="email" required>
									</div>
									<div class="form-group">
										<label for="password">Password:</label> <input type="password"
											class="form-control" id="password" name="password" required>
									</div>
									<div class="form-group">
										<label for="admin">Admin:</label> <select id="admin"
											name="admin" class="form-control" required>
											<option selected="">Choose</option>
											<option value="1">Yes</option>
											<option value="0">No</option>
										</select>
									</div>
									<br>
									<button type="submit" name="action" value="Sign Up"
										class="btn btn-primary" align="center">Sign Up</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		function RegValidate() {
			var first_name = document.forms["RegForm"]["first_name"];
			var last_name = document.forms["RegForm"]["last_name"];
			var qualification = document.forms["RegForm"]["qualification"];
			var email = document.forms["RegForm"]["email"];
			var password = document.forms["RegForm"]["password"];
			var admin = document.forms["RegForm"]["admin"];

			if (first_name.value == "") {
				window.alert("Please enter your First name");
				first_name.focus();
				return false;
			}

			if (last_name.value == "") {
				window.alert("Please enter your Last name");
				last_name.focus();
				return false;
			}

			if (qualification.value == "") {
				window.alert("Please enter your Highest Qualification");
				qualification.focus();
				return false;
			}

			if (email.value == "") {
				window.alert("Please enter a valid e-mail address.");
				email.focus();
				return false;
			}

			if (email.value.indexOf("@", 0) < 0) {
				window.alert("Please enter a valid e-mail address.");
				email.focus();
				return false;
			}

			if (email.value.indexOf(".", 0) < 0) {
				window.alert("Please enter a valid e-mail address.");
				email.focus();
				return false;
			}

			if (password.value == "") {
				window.alert("Please enter your password");
				password.focus();
				return flase;
			}

			if (phone.value == "") {
				window.alert("Please enter your telephone number.");
				phone.focus();
				return false;
			}

			if (admin.value == "") {
				alert("Please enter whether you are an Admin or NOT");
				admin.focus();
				return false;
			}

			return true;
		}

		function LoginValidate() {
			var email = document.forms["Login"]["email"];
			var password = document.forms["Login"]["password"];

			if (email.value == "") {
				window.alert("Error: E-mail cannot be blank!");
				email.focus();
				return false;
			}

			if (email.value.indexOf("@", 0) < 0) {
				window.alert("Please enter a valid e-mail address.");
				email.focus();
				return false;
			}

			if (email.value.indexOf(".", 0) < 0) {
				window.alert("Please enter a valid e-mail address.");
				email.focus();
				return false;
			}

			if (password.value == "") {
				window.alert("Error: Password cannot be blank!");
				password.focus();
				return flase;
			}

			return true;
		}
	</script>
</body>
</html>