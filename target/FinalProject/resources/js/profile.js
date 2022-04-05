$(document).ready(function() {
	$.ajax({
				type: "post",
				url: "GetOneUserData",
				data: { "UserId": UserId },
				"datatype": "json",
				success: function() {
					console.log(data);
				},
				error: function(textStatus) {
					alert("not call")
				},
		})	
});

