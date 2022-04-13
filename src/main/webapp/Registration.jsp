<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">

    <!-- Title Page-->
    <title>New User Registration Form</title>

    <!-- Icons font CSS-->
    <link href="resources/reg/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="resources/reg/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="resources/reg/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="resources/reg/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="resources/reg/css/main.css" rel="stylesheet" media="all">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="resources/reg/css/style.css">
    
    <!-- custom alert cdn -->
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
   
    
</head>

<body>
<%
response.setHeader("Cache-Control", "no-cache , must-revalidate"); //HTTP 1.1   must-revalidate

response.setHeader("Pragma", "no-cache"); //HTTP 1.0

response.setHeader("Expires" ,"0"); //Proxy
%>
<c:choose>
		<c:when test="${param.back == 'Registration' || requestScope.back == 'Registration' }">
	<c:if test="${sessionScope.username != null}">
		<jsp:include page="Header.jsp"></jsp:include>
	</c:if>
	
    <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
        <div class="wrapper wrapper--w790">
            <div class="card card-5">
                <div class="card-heading">
                <c:if test="${param.status == 'edituser' }">
                	<h2 class="title"> Edit Profile </h2>
                    <h4 id="result">${requestScope.error }</h4>
                    <input type="hidden" id="userid" value="${param.UserId }" />
                    <input type="hidden" id="hiddentype" value="${param.status }" />
                </c:if>
                <c:if test="${requestScope.status == 'edituser'}">
                	<h2 class="title"> Edit Profile </h2>
                    <h4 id="result">${requestScope.error }</h4>
                    <input type="hidden" id="userid" value="${requestScope.UserId }" />
                    <input type="hidden" id="hiddentype" value="${requestScope.status }" />
                </c:if>
                <c:if test="${param.status == 'adduser' || requestScope.status == 'adduser'}">
                	<h2 class="title"> Add User </h2>
                    <h4 id="result">${requestScope.error }</h4>
                    <input type="hidden" id="hiddentype" value="${param.status }" />
                </c:if>
                <c:if test="${sessionScope.username == null }">
                    <h2 class="title"> Registration Form</h2>
                    <h4 id="result">${requestScope.error }</h4>
                </c:if>
                </div>
                <div class="card-body">
                    <form id="form" name="reg_form" action="RegController" method="POST" enctype="multipart/form-data">
                        <div class="form-row m-b-55">
                            <div class="name">Name</div>
                            <div class="value">
                                <div class="row row-space">
                                    <div class="col-2">
                                        <div class="input-group-desc">
                                            <label class="label--desc">First Name</label>
                                            <input class="input--style-5" type="text" name="first_name" id="fname" value="${requestScope.first_name }" placeholder="Firstname">
                                        </div>
                                    </div>
                                    <div class="col-2">
                                        <div class="input-group-desc"> 
                                            <label class="label--desc">Last Name</label>                                          
                                            <input class="input--style-5" type="text" name="last_name" id="lname" value='<c:out value="${requestScope.last_name }"></c:out>' placeholder="Lastname">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Email</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="email" name="email"
										id="email" onchange='checkEmail()' id="email" value='<c:out value="${requestScope.email }"></c:out>'>
										<div id = "emailStatus"></div>
                                </div>
                            </div>
                        </div>
                        
	                        <div class="form-row" id="passdiv">
	                            <div class="name">Password</div>
	                            <div class="value">
	                                <div class="input-group">
	                                    <input class="input--style-5" type="password" name="password" id="pass" >
	                                </div>
	                            </div>
	                        </div>
	                        <div class="form-row" id="conpassdiv">
	                            <div class="name">Confirm Password</div>
	                            <div class="value">
	                                <div class="input-group">
	                                    <input class="input--style-5" type="password" name="conpass"  id="confirm" >
	                                    <span id="result"> </span>
	                                </div>
	                            </div>
	                        </div>
                        
                        <div class="form-row m-b-55">
                            <div class="name">Date Of Birth</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-4"  type="date" name="date" id="dob" value='<c:out value="${requestScope.date }"></c:out>'>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row m-b-55">
                            <div class="name">Phone</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <label class="label--desc">Phone Number</label>
                                            <input class="input--style-5" type="text" name="phone" id="phone" value='<c:out value="${requestScope.phone }"></c:out>'>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row ">
                            <div class="name">Gender</div>
                            <div class="p-t-15">
                                <label class="radio-container m-r-55">Male
                                    <input type="radio" checked="checked" name="gender" id="male" value="male">
                                    <span class="checkmark"></span>
                                </label>
                                <label class="radio-container">Female
                                    <input type="radio" name="gender" value="female" id="female">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Language</div>
                            <div class="p-t-15">
                                <label class="radio-container m-r-45">
                                    <input type="checkbox" id="chk1" class="form-check-input" name="checkbox" value="Java">Java
                                    <span class="check"></span>
                                </label>
                                <label class="radio-container m-r-45">
                                    <input type="checkbox" id="chk2" class="form-check-input" name="checkbox" value="Python">Python
                                    <span class="check"></span>
                                </label>
                                <label class="radio-container ">
                                    <input type="checkbox" id="chk3" class="form-check-input" name="checkbox" value="C++">C++
                                    <span class="check"></span>
                                </label>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name"> Image Upload</div>
                            <div class="value upload-image">
                                <label for="new_image" class="custom-file-upload"><i class="fa fa-cloud-upload"></i> Image Upload</label>
                                <input id="new_image" type = "file"  name = "image" accept=".jpg, .jpeg, .png " />
                            </div>
                        </div>
                        <div >
                            <img id="show_image" width="100" height="100">
                        </div>
                        <div data-duplicate="demo">
                        <fieldset>
                            <legend>Address</legend>
                            <input type="hidden" id="addressid" name="addressid">
                            <div class="form-row">
                                <div class="value">
                                        <div class="input-group">
                                            <div class="row row-space">
                                                <div class="input-group-desc m-b-40">
                                                    <label class="label--desc ">Address</label>
                                                    <textarea class="input--style-5 " name="address[]" rows="4" cols="50" id="address" required></textarea>
                                                    <label id="address-error" class ="error" for="address"></label>
                                                </div>
                                            </div>
                                            <div class="row row-space">
                                                <div class="col-2">
                                                    <div class="input-group-desc m-b-40">
                                                        <label class="label--desc">Zipcode</label>
                                                        <input class="input--style-5 w-50 m-t-b-20" id="zip" type="text" name="zip[]" placeholder="Zipcode" >
                                                    </div>
                                                </div>
                                                <div class="col-2">
                                                    <div class="input-group-desc m-b-40"> 
                                                        <label class="label--desc">City</label>                                          
                                                        <input class="input--style-5 w-50 m-t-b-20" id="city" type="text" name="city[]" placeholder="City" >
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row row-space">
                                                <div class="col-2">
                                                    <div class="input-group-desc m-b-40">
                                                        <label class="label--desc">State</label>
                                                        <input class="input--style-5 w-50 m-t-b-15" id="state" type="text" name="state[]" placeholder="State" >
                                                    </div>
                                                </div>
                                                <div class="col-2">
                                                    <div class="input-group-desc m-b-40"> 
                                                        <label class="label--desc">Country</label>                                          
                                                        <input class="input--style-5 w-50 m-t-b-15" id="country" type="text" name="contry[]" placeholder="Contry" >
                                                    </div>
                                                </div>
                                                <button class="custombtn btn--blue remove_btn invisible n-m-b-20" type="button">Remove</button>
                                                <button class="custombtn n-m-b-20" id="remove" data-duplicate-remove="demo" type="button">- Remove</button>
                                            </div>
                                        </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                        <button class="btn btn--radius btn--blue m-b-10" id="add" data-duplicate-add="demo" type="button">+ ADD</button>
                        <div>
                            <button class="btn btn--radius-2 btn--red" type="submit" id="submit">Register</button>
                            <button class="btn btn--radius-2 btn--red invisible" type="button" id="update">Update</button>
                            <button class="btn btn--radius-2 btn--red" type="reset">Clear Form</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    	<c:if test="${sessionScope.username != null}">
    		<footer>
				<%@ include file="Footer.jsp"%>
			</footer>
		</c:if>	
    
    </c:when>
		<c:otherwise>
			<jsp:forward page="index.jsp"></jsp:forward>
		</c:otherwise>
	</c:choose>

    <!-- Jquery JS-->
    <script src="resources/reg/vendor/jquery/jquery.min.js"></script>
    <!-- Validation js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <!-- Vendor JS-->
    <script src="resources/reg/vendor/select2/select2.min.js"></script>
    <script src="resources/reg/vendor/datepicker/moment.min.js"></script>
<!--     <script src="resources/reg/vendor/datepicker/daterangepicker.js"></script> -->

    <script src="resources/reg/js/jquery.duplicate.js"></script>

    <!-- Main JS-->
    <script src="resources/reg/js/global.js"></script>
    <!-- Custom JS -->
    <script src="resources/reg/js/custom.js"></script>

</body>

</html>
<!-- end document-->