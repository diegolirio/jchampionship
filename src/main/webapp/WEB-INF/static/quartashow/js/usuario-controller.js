$(function() {
	
	$('#id_login').click(function(e) {
		
		e.preventDefault();
		
		var email = $('form').find('input[name=email]').val();
		var senha = $('form').find('input[name=senha]').val();;
		
		$.post( $(this).attr('href'), 
				{ email: email,
				  senha: senha },
				function(data, statusText, response) {
					  
					  alert(JSON.stringify(response));
					  
		}).fail(function(data) {
			if(data.status == 401) {
				
				var messageAlert = "";
				messageAlert += data.responseJSON.errorMessages.email == undefined ? "" : "Email: " + data.responseJSON.errorMessages.email; 
				messageAlert += data.responseJSON.errorMessages.senha == undefined ? "" : "<br/>Senha: " + data.responseJSON.errorMessages.senha;
				messageAlert += data.responseJSON.errorMessages.login == undefined ? "" : data.responseJSON.errorMessages.login;
				
				$('.class_message').show();
				$('#id_alert_message').html(messageAlert);
			}
			alert(JSON.stringify(data));
		});
		
	});
	
});