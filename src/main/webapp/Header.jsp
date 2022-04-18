<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header File</title>
<!-- Bootstrap -->
    <link href="resource/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- custom css -->
<link href="resource/css/header.css" rel="stylesheet">
    
    <%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1   must-revalidate

response.setHeader("Pragma", "no-cache"); //HTTP 1.0

response.setHeader("Expires" ,"0"); //Proxy
%>
</head>
<body>

	<c:choose>
		<c:when test="${sessionScope.username != null }">
			<nav class="navbar navbar-default navbar-static-top">
		        <div class="container">
		          <!-- Brand and toggle get grouped for better mobile display -->
		          <div class="navbar-header">
		            <a class="navbar-brand" href="#">WebsiteName</a>
		          </div>      
		          <!-- Collect the nav links, forms, and other content for toggling -->
		          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		            <ul class="nav navbar-nav">
		            	<c:if test="${sessionScope.role == 'Admin' }">
		              		<li class="active"><a href="Admin-Dashboard.jsp">Home <span class="sr-only">(current)</span></a></li>
		              		<li><a href="Profile.jsp">Profile</a></li>
		              	</c:if>
		              	<c:if test="${sessionScope.role == 'User' }">
		              		<li class="active"><a href="User-Dashboard.jsp">Home <span class="sr-only">(current)</span></a></li>
		              	</c:if>
		              	<li><a href="logout">Logout</a></li>
		            </ul>
		          </div><!-- /.navbar-collapse -->
		        </div><!-- /.container -->
		    </nav>
	
		</c:when>
		<c:otherwise>
			<jsp:forward page="index.jsp"></jsp:forward>
		</c:otherwise>
	</c:choose>
      
      <script src="resource/bootstrap/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="resource/bootstrap/js/header.js"></script>
</body>
</html>