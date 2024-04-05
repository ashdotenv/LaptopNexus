<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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

    .login-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .login-box {
        background-color: #ffffff;
        border-radius: 20px;
        padding: 30px;
        box-shadow: 5px 5px 15px #bfbfbf, -5px -5px 15px #ffffff;
    }

    .form-label {
        font-weight: bold;
    }

    .login-button {
        border-radius: 15px;
    }

  

    .success-message {
        color: #00ff00;
    }
</style>
</head>
<body>

<div class="login-container">
    <div class="login-box">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <div class="form-group">
                <label for="username" class="form-label">Username:</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="password" class="form-label">Password:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary  btn-block">Login</button>
            <div class="mt-3 text-center">
				<span>Don't have an account? <a href="${pageContext.request.contextPath}/RegisterServlet">Register</a></span>
			</div>
        </form>

        <% 
        String successMessage = (String) request.getAttribute(StringUtils.SUCCESS_MESSAGE);
        String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);

        if (errorMessage != null && !errorMessage.isEmpty()) {
        %>
        <!-- Display error message -->
         <div class="alert alert-danger mt-2" role="alert">
            <%= errorMessage %>
        </div>
        <% } %>

        <% 
        if (successMessage != null && !successMessage.isEmpty()) {
        %>
        <!-- Display success message -->
        <div class="alert alert-success mt-2" role="alert">
            <%= successMessage %>
        </div>
        <% } %>

    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
