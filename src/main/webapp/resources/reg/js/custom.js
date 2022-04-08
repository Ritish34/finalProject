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
	
	$("input#confirm").change(function(){
		let con = $(this).val().trim();
		let pass = $("#pass").val().trim();
		console.log(con+"   "+pass);
		if(con === pass){
			$("span#result").html("Password matched").css("color","green");
			console.log("matched");
		}
		else{
			$("span#result").html("Password is not match with confirm password!!").css("color","red");
			$(this).focus();
		}
	});
	
	$("input#pass").change(function(){
		let pass = $(this).val().trim();
		let con = $("#confirm").val().trim();
		console.log(con+"   "+pass);
		if(con != null){
			if(con === pass){
			$("span#result").html("Password matched").css("color","green");
			console.log("matched");
		}
		else{
			$("span#result").html("Password is not match with confirm password!!").css("color","red");
			$(this).focus();
		}
		}
		
	});	
	
	//call ajax for edit Profile
		$.ajax({
				type: "post",
				url: "GetOneUserData",
				datatype: "json",
				success: function(r) {		
					console.log(r);
					console.log(r.data[0].fname);
					$("#fname").val(r.data[0].fname);
					$("#lname").val(r.data[0].lname);
					$("#email").val(r.data[0].email);
					$("#email").prop('readonly',true);
					$("#phone").val(r.data[0].phone);
					$("#phone").prop('readonly',true);
					$("#dob").val(r.data[0].dob);
//					$("#lang").html(r.data[0].lang);

					let lang = r.data[0].lang;
					 debugger
//                		arr.includes($(this).val()) ? $(this).prop('checked', '') : $(this).prop('checked', 'checked')
					if(lang.includes($("#chk1").val() )){
						$("#chk1").prop('checked', 'checked')
					}
					if(lang.includes($("#chk2").val() )){
						$("#chk2").prop('checked', 'checked')
					} 
					if(lang.includes($("#chk3").val() )){
						$("#chk3").prop('checked', 'checked')
					}
            		
            		let gen = r.data[0].gender;
					if(gen == "male"){
						$("#male").prop('checked','checked');
					}
					else{
						$("#female").prop('checked','checked');
					}
					$("img#show_image").attr("src","data:image/jpg;base64,"+r.data[0].base64Image);
				},
				error: function(textStatus) {
					alert("not call")
				},
		}); 

let addresslist = new Array();		
		//call ajax for Address filed
		$.ajax({
				type: "post",
				url: "GetAddressData",
				datatype: "json",
				success: function(r) {		
					console.log(r);
				$.each(r, function(key, value) {
    				$.each(value, function(key, value) {
						$("#form fieldset:last #addressid").val(value.addressid);
						$("#form fieldset:last #address").val(value.address);
						$("#form fieldset:last #zip").val(value.zip);
						$("#form fieldset:last #city").val(value.city);
						$("#form fieldset:last #state").val(value.state);
						$("#form fieldset:last #country").val(value.contry);
						
						if(key+1 < Object.keys(r.data).length){
							$('#add').click();
						}
						addresslist.push(value.addressid);
					});
				});
				},
				error: function(textStatus) {
					alert("not call")
				},
		}); 
		
		let arr = new Array();
		
		//on submit buttun
		$("#update").on('click',function(e){
			
			$("#form").attr('action','UpdateProfile')
			//call ajax to delete address
			e.preventDefault();
			if(arr.length != 0){
				$.ajax({
					type: "post",
					url: "DeleteAddress",
					data:{ Array : arr},
					datatype: "json",
					success: function(r) {		
						console.log(r);
					},
					error: function(textStatus) {
						console.log("not call deleteAddress")
					},
				});	
			}
			
			$("#submit").click();
			
			/*var myForm = $("#form")[0];
			var formdata = new FormData(form);
//			var formdata = $("#form").serialize();
			console.log(formdata);
			$.ajax({
	            url: "UpdateProfile",
	            type: "POST",
	            contentType:"multipart/form-data",
	            data: formdata ,
//	            contentType: false,
//				dataType:"json",
	            cache: false,
	            async: true,
	            processData:false,
	            success: function(res){
	                alert(res);
	            },
	            error: function(){
	                console.log("not call updateprofile");
	            }           
        	});*/		
		});
		
		$('#form').on('click', '.remove_btn', function (){
			let remove = $(this).siblings('input#addressid').val();
			if(remove != ""){
				if(addresslist.length > 1){
					addresslist.pop(remove);
					arr.push(remove);
				}	
			}
			$(this).siblings('#remove').click();
			console.log(arr);
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

/*//
function submitData(){
	var form = document.getElementById("form"); 
}*/

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

