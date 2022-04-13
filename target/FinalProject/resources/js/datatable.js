
$(document).ready(function() {

	var table = $('#table_id').DataTable({

		"ajax": {
			"url": "ShowAllUser",
			"type": "GET",
			"datatype": "json"
		},
		"columns": [
			{ "data": "id" },
			{ "data": "fname" },
			{ "data": "lname" },
			{ "data": "dob" },
			{ "data": "phone" },
			{ "data": "gender" },
			{ "data": "lang" },
			{ "data": "email" },
			{ "defaultContent": "	<a class='btn btn-danger' id='delete-btn' role='button'>Delete</a>" },
			{ "defaultContent": "	<a class='btn btn-info' id='update-btn' role='button'>Update</a>" },
		]
	});

	$('#table_id').on('click', '#delete-btn', function(event) {
		event.preventDefault();
		var data = table.row($(this).parents('tr')).data();
		var UserId = data.id;
		console.log(UserId);

		$.ajax({
			type: "post",
			url: "DeleteUser",
			data: { "UserId": UserId },
			success: function() {
				alert("ok")
				table.ajax.reload();
			},
			error: function(textStatus) {
				alert("not call")
			},
		})

	});
	
	$('#table_id').on('click', '#update-btn', function(event) {
		event.preventDefault();
		var data = table.row($(this).parents('tr')).data();
		var UserId = data.id;
		console.log(UserId);

		var url = "Registration.jsp?back=Registration&status=edituser&UserId="+UserId;
		window.location = url;

	});
});