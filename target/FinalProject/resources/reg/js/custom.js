//Start JQuery
$(function() {
	//make validation method to validate regex using regex pattern
	$.validator.addMethod(
		"regex",
		function(value, element, regexp) {
			var check = false;
			return this.optional(element) || regexp.test(value);
		},
		jQuery.validator.format("Formate Mismatched")
	);
	
	//set lazzy to eager validation
	$("form[name='reg_form']").validate({
		onfocusout: function(element) {
			// "eager" validation
			this.element(element);
		},
		// Specify validation rules
		rules: {
			first_name: {
				required: true,
				minlength: 3,
				regex: /^([a-zA-Z])+(\s)*$/
			},
			last_name: {
				required: true,
				minlength: 3,
				regex: /^([a-zA-Z])+(\s)*$/
			},
			email: {
				required: true,
				email: true,
				regex: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/
			},
			password: {
				required: true,
				minlength: 8,
				maxlength: 20,
				regex: /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/
			},
			address: {
				required: true
			},
			city: {
				required: true
			},
			contry: {
				required: true
			}
		},
		// Specify validation error messages
		messages: {
			firstname: {
				required: "Please enter your firstname",
			},
			lastname: {
				required: "Please enter your lasttname",
			},
			password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 8 characters long",
				regex: "Password must contain Minimum eight characters, at least one letter, one number and one special characte"
			},
			email: "Please enter a valid email address",
		},
		submitHandler: function(form) {
			form.submit();
		}
	});
});

//create function to check entered eamail is already present into database or not 
function checkEmail() {
		//get email value
		var emailInput = document.querySelector('#email').value;
		//make ajax call to fetch data from db
		$.ajax({
			method: "POST",
			url: "CheckEmail",
			data: { emailId: emailInput },
			success: function(data) {
				if(data === "Duplicate"){
					$('#emailStatus').html("Email is Already Registered");
					$('#email').focus();	// rediret focus to email input tag
				}
				else{
					$('#emailStatus').html(" ");
				}
			}
		});
	}