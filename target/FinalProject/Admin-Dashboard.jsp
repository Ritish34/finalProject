<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	overflow-x: hidden;
}

body {
	display: flex;
	flex-direction: column;
}

main {
	flex-grow: 1;
}
.capitalize {
  text-transform: capitalize;
}
</style>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.css">

</head>
<body>
	<header>
		<%@ include file="Header.jsp"%>
	</header>

	<h3 class="capitalize">Hello <c:out value="${sessionScope.username}"></c:out> </h3>
	<main>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div>
						<table id="table_id" class="display">
							<thead>
								<tr>
									<th>UserId</th>
									<th>FirstName</th>
									<th>LastName</th>
									<th>Dob</th>
									<th>MobailNo</th>
									<th>Gender</th>
									<th>language</th>
									<th>Email</th>
									<th>Delete</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-md-12">
					<button>
						<a href="Registration.jsp"> Add New User</a>
					</button>
				</div>
			</div>
		</div>
	</main>
	<footer>
		<%@ include file="Footer.jsp"%>
	</footer>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<script type="text/javascript" charset="utf8"
		src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="resources/js/datatable.js"></script>
</body>
</html>