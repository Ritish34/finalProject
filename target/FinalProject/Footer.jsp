<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Footer</title>
<!-- Bootstrap -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.container{
	background: white;
/*  	border : 1px solid black; */
	padding : 10px 10px;
	width: 100%;
}
p{
	font-size : 15px;
	color: black;
}
</style>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1   must-revalidate

response.setHeader("Pragma", "no-cache"); //HTTP 1.0

response.setHeader("Expires" ,"0"); //Proxy
%>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.username != null }">
			<div class="container">
				<p>@Copyrights</p>
			</div>
	
		</c:when>
		<c:otherwise>
			<jsp:forward page="index.jsp"></jsp:forward>
		</c:otherwise>
	</c:choose> 
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>