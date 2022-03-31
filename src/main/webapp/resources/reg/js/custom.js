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
			},
			zip : {
				required : true
			},
			state: {
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
	
	/*$("form").submit(function(event)
	{
		const formdata = new FormData("#form");
		console.log($("#form").serialize());
		var form = document.getElementById("form");
		console.log(new FormData(form));
		$.ajax({
			method:"POST",
			url:"RegController",
			data: {first_name : "ritish"},//$("#form").serialize()
			enctype : "multiform/form-data",
			
			success:function(data){
				console.log(data);
				if(data === "Success"){
					$('#result').html("Registration Sucessfull").css("color","green");
				}
				else
				{
					$('#result').html("Registration Fails!!").css("color","red");
				}
			},
			processData:false,
			contentType:false,
			error: function(){
                alert("error");
            } 
		});
		event.preventDefault();
	});*/
});


//create function to check entered eamail is already present into database or not 
function checkEmail() {
		//get email value
		var emailInput = document.querySelector('#email').value;
		//make ajax call to fetch data from db
		$.ajax({
			method: "POST",
			url: "CheckEmail",
			async: false,
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

//
function submitData(){
	var form = document.getElementById("form"); 
}

function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    
    reader.onload = function (e) {
      $('#show_image').attr('src', e.target.result);
    }
    reader.readAsDataURL(input.files[0]);
  }
}
function clearFile(){
  const file =document.querySelector('#new_image');
      file.value = '';
      console.log("ok");
}
$("#new_image").change(function(){
    //value of file
    var imgName = $(this)[0].value;

    //find extatntion of file
    var extn = imgName.substring(imgName.lastIndexOf('.') + 1).toLowerCase();
    //vallidation of image
    if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg"){
      readURL(this);
    }
    else{
      clearFile();
    } 
});

/*$(document).ready(function(){

	//stop form to submit
	$("#form").submit(function(e){
		e.preventDefault();
	});

	//check for button click event
	$("#submit").click(function(e){
          
		//get the form data and then serialize that
		dataString = $("#form").serialize();
		$.ajax({
            url: "RegController",
            type: "POST",
            data:  dataString,
            contentType: false,
            cache: false,
            processData:false,
            success: function(responseText){
                alert(responseText);
            },
            error: function(){
                alert("error");
            }           
        });
	});	
})*/