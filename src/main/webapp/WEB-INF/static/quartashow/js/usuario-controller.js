$(function() {
	
	$('#form').submit(function(e) {
		e.preventDefault();
		
		var email = $('form').find('input[name=email]').val();
		var senha = $('form').find('input[name=senha]').val();;
		
		$.post( $(this).attr('href'), 
				{ email: email,
				  senha: senha },
				function(data, statusText, response) {
					  var _get =_GET("next");
					  if(isEmpty(_get)) 
						  window.location.href = "/jchampionship"+response.getResponseHeader("Location");
					  else
						  window.location.href = _get;
					  
		}).fail(function(data) {
			if(data.status == 401) {
				
				var messageAlert = "";
				messageAlert += data.responseJSON.errorMessages.email == undefined ? "" : "<b>Email:</b> " + data.responseJSON.errorMessages.email; 
				messageAlert += data.responseJSON.errorMessages.senha == undefined ? "" : "<br/><b>Senha</b>: " + data.responseJSON.errorMessages.senha;
				messageAlert += data.responseJSON.errorMessages.login == undefined ? "" : data.responseJSON.errorMessages.login;
				
				$('.class_message').show();
				$('#id_alert_message').html(messageAlert);
			} else {
				alert("Erro: \n" + JSON.stringify(data));
			}
		});
		
	});
	
});