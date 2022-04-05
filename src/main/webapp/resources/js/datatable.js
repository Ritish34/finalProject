
/* $(document).ready(function(){
    //call table plugin
var table =$('#empTable').DataTable({
                "ajax":"",
                "columns": [                                        
                    {"data" : "id"},
                    {"data" : "employee_name"},
                    {"data" : "employee_salary"},
                    {"data" : "employee_age"},
                    {"data" : "profile_image"},
                ],
            });
});*/
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
			{ "defaultContent": "	<a class='btn btn-danger' id='delete-btn' role='button'>Delete</a>" }
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
});