<%@page import="model.UserModel"%>
<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Form</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	background-color: #f0f0f0;
}

.container {
	margin-top: 50px;
}

.card {
	background-color: #ffffff;
	border-radius: 20px;
	border: none;
	box-shadow: 5px 5px 15px #bfbfbf, -5px -5px 15px #ffffff;
}

.form-control {
	border-radius: 15px;
}

.btn-primary {
	border-radius: 15px;
}

h1 {
	text-align: center;
	margin-bottom: 30px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card p-4">
					<h1>Registration Form</h1>
					<%
					String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
					if (errorMessage != null && !errorMessage.isEmpty()) {
					%>
					<div class="alert alert-danger" role="alert">
						<%=errorMessage%>
					</div>
					<%
					}
					%>
					<form action="${pageContext.request.contextPath}/RegisterServlet"
						method="post">
						<div class="form-group">
							<label for="firstName">First Name:</label> <input type="text"
								class="form-control" id="firstName" name="first_name" required>
						</div>
						<div class="form-group">
							<label for="lastName">Last Name:</label> <input type="text"
								class="form-control" id="lastName" name="last_name" required>
						</div>
						<div class="form-group">
							<label for="username">Username:</label> <input type="text"
								class="form-control" id="username" name="username" required>
						</div>
						<div class="form-group">
							<label for="email">Email:</label> <input type="email"
								class="form-control" id="email" name="email" required>
						</div>
						<div class="form-group">
							<label for="phoneNumber">Phone Number:</label> <input type="tel"
								class="form-control" id="phoneNumber" name="phone_number"
								required>
						</div>
						<div class="form-group">
							<label for="password">Password:</label> <input type="password"
								class="form-control" id="password" name="password" required>
						</div>
						<button type="submit" class="btn btn-primary btn-block">Submit</button>
					</form>
					<div class="mt-3 text-center">
						<span>Already Have an Account? <a
							href="${pageContext.request.contextPath}/LoginServlet">Login</a></span>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
