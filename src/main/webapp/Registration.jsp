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
    <!-- <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates"> -->

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
</head>

<body>
	
	<c:if test="${sessionScope.username != null}">
		<jsp:include page="Header.jsp"></jsp:include>
	</c:if>
	
    <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
        <div class="wrapper wrapper--w790">
            <div class="card card-5">
                <div class="card-heading">
                <c:if test="${sessionScope.username != null}">
                	<h2 class="title"> Edit Profile </h2>
                    <h4 id="result"></h4>
                </c:if>
                <c:if test="${sessionScope.username == null }">
                    <h2 class="title"> Registration Form</h2>
                    <h4 id="result"></h4>
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
                                            <input class="input--style-5" type="text" name="first_name" id="fname" value='<c:out value="${param.first_name }"></c:out>' placeholder="Firstname">
                                        </div>
                                    </div>
                                    <div class="col-2">
                                        <div class="input-group-desc"> 
                                            <label class="label--desc">Last Name</label>                                          
                                            <input class="input--style-5" type="text" name="last_name" id="lname" value='<c:out value="${param.last_name }"></c:out>' placeholder="Lastname">
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
										id="email" onchange='checkEmail()' id="email" value='<c:out value="${param.email }"></c:out>'>
										<div id = "emailStatus"></div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${sessionScope.username == null }">
	                        <div class="form-row">
	                            <div class="name">Password</div>
	                            <div class="value">
	                                <div class="input-group">
	                                    <input class="input--style-5" type="password" name="password" id="pass" value='<c:out value="${param.password }"></c:out>'>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="form-row">
	                            <div class="name">Confirm Password</div>
	                            <div class="value">
	                                <div class="input-group">
	                                    <input class="input--style-5" type="password" name="conpass" value='<c:out value="${param.password }"></c:out>' id="confirm" required>
	                                    <span id="result"> </span>
	                                </div>
	                            </div>
	                        </div>
                        </c:if>
                        <div class="form-row m-b-55">
                            <div class="name">Date Of Birth</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-4" type="date" name="date" id="dob" value='<c:out value="${param.date }"></c:out>'>
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
                                            <input class="input--style-5" type="text" name="phone" id="phone" value='<c:out value="${param.phone }"></c:out>'>
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
                                <input id="new_image" type = "file"  name = "image" accept=".jpg, .jpeg, .png " />REQUIRED
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
                                                    <textarea class="input--style-5 " name="address[]" rows="4" cols="50" id="address"></textarea>
                                                </div>
                                            </div>
                                            <div class="row row-space">
                                                <div class="col-2">
                                                    <div class="input-group-desc m-b-40">
                                                        <label class="label--desc">Zipcode</label>
                                                        <input class="input--style-5 w-50 m-t-b-20" id="zip" type="text" name="zip[]" placeholder="Zipcode">
                                                    </div>
                                                </div>
                                                <div class="col-2">
                                                    <div class="input-group-desc m-b-40"> 
                                                        <label class="label--desc">City</label>                                          
                                                        <input class="input--style-5 w-50 m-t-b-20" id="city" type="text" name="city[]" placeholder="City">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row row-space">
                                                <div class="col-2">
                                                    <div class="input-group-desc m-b-40">
                                                        <label class="label--desc">State</label>
                                                        <input class="input--style-5 w-50 m-t-b-15" id="state" type="text" name="state[]" placeholder="State">
                                                    </div>
                                                </div>
                                                <div class="col-2">
                                                    <div class="input-group-desc m-b-40"> 
                                                        <label class="label--desc">Country</label>                                          
                                                        <input class="input--style-5 w-50 m-t-b-15" id="country" type="text" name="contry[]" placeholder="Contry">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                </div>
                            </div>
                            <button class=" btn--blue remove_btn" type="button">removeeeeeeeeee</button>
                            <button class=" btn--blue" id="remove" data-duplicate-remove="demo" type="button">- remove</button>
                        </fieldset>
                    </div>
                        <button class="btn btn--radius btn--blue" id="add" data-duplicate-add="demo" type="button">+ add</button>
                        <div>
                            <button class="btn btn--radius-2 btn--red" type="submit" id="submit">Register</button>
                            <button class="btn btn--radius-2 btn--red" type="button" id="update">Update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <script src="resources/reg/vendor/jquery/jquery.min.js"></script>
    <!-- Validation js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <!-- Vendor JS-->
    <script src="resources/reg/vendor/select2/select2.min.js"></script>
    <script src="resources/reg/vendor/datepicker/moment.min.js"></script>
    <script src="resources/reg/vendor/datepicker/daterangepicker.js"></script>

    <script src="resources/reg/js/jquery.duplicate.js"></script>

    <!-- Main JS-->
    <script src="resources/reg/js/global.js"></script>
    <!-- Custom JS -->
    <script src="resources/reg/js/custom.js"></script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->