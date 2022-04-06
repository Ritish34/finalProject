$(document).ready(function() {
	debugger
	
	$.ajax({
				type: "post",
				url: "GetOneUserData",
				
				datatype: "json",
				success: function(r) {
//					alert("ok");
					console.log(r);
					console.log(r.data[0].fname);
					$("h6#fname").html(r.data[0].fname);
					$("h6#lname").html(r.data[0].lname);
					$("h6#email").html(r.data[0].email);
					$("h6#phone").html(r.data[0].phone);
					$("h6#dob").html(r.data[0].dob);
					$("h6#lang").html(r.data[0].lang);
					$("h6#gender").html(r.data[0].gender);
					$("img#image").attr("src","data:image/jpg;base64,"+r.data[0].base64Image);
				},
				error: function(textStatus) {
					alert("not call")
				},
		});	
});

