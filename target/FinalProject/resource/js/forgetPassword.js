$(document).ready(function() {

    $("#submit").click(function(e){

        e.preventDefault ();
        var emailInput = document.querySelector('#email').value;

        $.ajax({
            method: "POST",
            url: "SendEmail",
            async: false,
            data: { email: emailInput },
            success: function(response) {
                if(response == "Sent message successfully...."){
                    alert("Sent message successfully....");
                }
                else{
                    alert("Opps,Something Went Wrong");
                }
            }
        });
    });


    //create function to check entered eamail is already present into database or not 
});

	debugger
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
                $('#emailStatus').html("");
                $("#submit").prop('disabled',false);
            }
            else{
                $('#emailStatus').html("Email is Not Registered");
                $('#email').focus();	// rediret focus to email input tag
                $("#submit").prop('disabled', true);
            }
        }
    });
}