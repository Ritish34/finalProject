<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Page</title>

<!-- Add Bootstrap CDN -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.6.95/css/materialdesignicons.css">

<!-- Add CSS File -->
<link rel="stylesheet" type="text/css" href="resource/css/profile.css">	

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1   must-revalidate

response.setHeader("Pragma", "no-cache"); //HTTP 1.0

response.setHeader("Expires" ,"0"); //Proxy
%>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.username != null }">
			<header>
			<%@ include file="Header.jsp"%>
			</header>
			<main>
				<div class="page-content page-container" id="page-content">
					<div class="padding">
						<div class="row container d-flex justify-content-center">
							<div class="col-md-12 col-xl-6">
								<div class="card user-card-full">
									<div class="row m-l-0 m-r-0">
										<div class="col-sm-4 col-sm-4 bg-c-lite-green user-profile">
											<div class="card-block text-center text-white">
												<div class="m-b-25">
													<img
														src="https://img.icons8.com/bubbles/100/000000/user.png"
														class="img-radius" alt="User-Profile-Image" id="image" width="100%" height="100%">
												</div>
												<h6 class="f-w-600"><c:out value="${requestScope.user.getFname()}"></c:out></h6>
												<img src="${requestScope.user.getImage()}">
												<!-- edit icon => <i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i> -->
											</div>
										</div>
										<div class="col-sm-8 col-sm-8">
											<div class="card-block">
												<h6 class="m-b-20 p-b-5 b-b-default f-w-600">User Details</h6>
												<div class="row">
													<div class="col-sm-6 col-sm-6">
														<p class="m-b-10 f-w-600">First Name</p>
														<h6 class="text-muted f-w-400" id="fname"></h6>
													</div>
													<div class="col-sm-6 col-sm-6">
														<p class="m-b-10 f-w-600">Last Name</p>
														<h6 class="text-muted f-w-400" id="lname"></h6>
													</div>
													<div class="col-sm-6 col-sm-6">
														<p class="m-b-10 f-w-600">Email</p>
														<h6 class="text-muted f-w-400" id="email"></h6>
													</div>
													<div class="col-sm-6 col-sm-6">
														<p class="m-b-10 f-w-600">Phone Number</p>
														<h6 class="text-muted f-w-400" id="phone"></h6>
													</div>
													<div class="col-sm-6 col-sm-6">
														<p class="m-b-10 f-w-600">Gender</p>
														<h6 class="text-muted f-w-400" id="gender"></h6>
													</div>
													<div class="col-sm-6 col-sm-6">
														<p class="m-b-10 f-w-600">Date Of Birth</p>
														<h6 class="text-muted f-w-400" id="dob"></h6>
													</div>
													<div class="col-sm-6 col-sm-6">
														<p class="m-b-10 f-w-600">Language</p>
														<h6 class="text-muted f-w-400" id="lang"></h6>
													</div>
													<div class="col-sm-12">
														<form name="main"  method="post" action="Registration.jsp">
															<input type="hidden" name="status" value="edituser" >
															<input type="hidden" name="back" value="Registration" >
       														<input type="submit" name="ter" value="Edit Profile" >
   														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
			<footer>
				<%@ include file="Footer.jsp"%>
			</footer>			
		</c:when>
		<c:otherwise>
			<jsp:forward page="index.jsp"></jsp:forward>
		</c:otherwise>
	</c:choose>

	
	<!-- Add Js File -->
	<script type="text/javascript"
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
		<script type="text/javascript" src="<c:url value = "resource/js/profile.js"/>" ></script>

</body>
</html>