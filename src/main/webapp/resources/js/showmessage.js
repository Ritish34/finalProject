$(document).ready(function(){
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      });

	var message = $("input#response").val();
	if(message == null || message == ""){

	}
	else if(message == 'error'){
		Toast.fire({
			icon: 'error',
			title: 'Oops,Something Wrong!! User can\'t be updated'
	  })
	}
	else{
		Toast.fire({
			icon: 'success',
			title: 'User Upadted'
	  })
	}  
	$("#response").remove();
});