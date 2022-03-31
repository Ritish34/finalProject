<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
<style type="text/css">
html, body { 
	height: 100%;
	margin: 0%;
	padding: 0%;
}
body { display: flex; flex-direction: column; }
main { flex-grow: 1; }
</style>
</head>
<body>
<header>
<%@ include file="Header.jsp" %>
</header>

<main>
<h2>Hello <%=session.getAttribute("username").toString().toUpperCase() %> </h2>
</main>

<footer>
<%@ include file="Footer.jsp" %>
</footer>

</body>
</html>