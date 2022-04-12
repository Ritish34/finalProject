<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forget Password</title>
</head>
<body>
	<h2>Forget Password</h2>
	<form action="SendEmail">
		<label for="email">Enter Email Id</label>
		<input type="text" id="email" name="email" onchange="checkEmail()" />
		<div id = "emailStatus"></div>
		<button type="submit" id="submit" >Send Password</button>
	</form>
	
	<script src="resources/reg/vendor/jquery/jquery.min.js"></script>
	<script src="resources/js/forgetPassword.js"></script>
</body>
</html>