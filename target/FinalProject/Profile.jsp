<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Page</title>

<!-- Add CSS File -->
<link rel="stylesheet" type="text/css" href="resources/css/profile.css">

<!-- Add Bootstrap CDN -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.6.95/css/materialdesignicons.css">


</head>
<body>
	
	<jsp:useBean id="user" scope="session" class="model.User"></jsp:useBean>
	
	<c:choose>
		<c:when test="${sessionScope.userid != 0}">
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
													class="img-radius" alt="User-Profile-Image">
											</div>
											<h6 class="f-w-600"><c:out value="${user.getFname() }"></c:out></h6>
											<!-- edit icon => <i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i> -->
										</div>
									</div>
									<div class="col-sm-8 col-sm-8">
										<div class="card-block">
											<h6 class="m-b-20 p-b-5 b-b-default f-w-600">Information</h6>
											<div class="row">
												<div class="col-sm-6 col-sm-6">
													<p class="m-b-10 f-w-600">Email</p>
													<h6 class="text-muted f-w-400">rntng@gmail.com</h6>
												</div>
												<div class="col-sm-6 col-sm-6">
													<p class="m-b-10 f-w-600">Phone</p>
													<h6 class="text-muted f-w-400">98979989898</h6>
												</div>
											</div>
											<h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600">Projects</h6>
											<div class="row">
												<div class="col-sm-6">
													<p class="m-b-10 f-w-600">Recent</p>
													<h6 class="text-muted f-w-400">Sam Disuja</h6>
												</div>
												<div class="col-sm-6">
													<p class="m-b-10 f-w-600">Most Viewed</p>
													<h6 class="text-muted f-w-400">Dinoter husainm</h6>
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
		</c:when>
		<c:otherwise>
			<c:redirect url="login.jsp" />
		</c:otherwise>
	</c:choose>



	<!-- Add Js File -->
	<script type="text/javascript"
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
		<script type="text/javascript" <c:url value = "resources/js/profile.js"/> ></script>

</body>
</html>