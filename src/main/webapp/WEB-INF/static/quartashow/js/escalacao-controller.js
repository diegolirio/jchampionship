$(function() {
	
	var form = $('#formJE');
	var formDeleteJE = $('#formDeleteJE');
	
	$(form).submit(function(e) {
		e.preventDefault();
		var jogadorEscalado = form.find('select[name="jogadorEscalado.id"]').val();
		if(jogadorEscalado == '') {
			alert('Selecione o Jogador');
			return false;
		}
		$.post( $(form).attr('action'), 
				{ jogadorEscaladoId: jogadorEscalado },
				function() {
					//alert(JSON.stringify(data));
					window.opener.location.reload();
					window.close();
				}
		).fail(function(data) {
			//console.log(data.responseText);
			alert(data.responseText); 
		});
	});
	
	// utilizado no escalacao-add-jogodorescalado.jsp
	$('#form').submit(function(e) {		
		e.preventDefault();		
		var jogadorId = $('#form').find('select[name="jogador.id"]').val();
		if(jogadorId == '') {
			alert('Selecione o jogador');
			return false;
		}
		$.post( $('#form').attr('action'),
				{ 'jogadorId':  jogadorId },
				function(data, statusText, response) {
					if(response.status == 201) {
						window.opener.location.reload();
						window.close();
					} else {
						alert('Erro:\n\n' + JSON.stringify(response));	
					}
					
		}).fail(function(data) {
			alert(JSON.stringify(data));
		});
	});
	
	// utilizado no escalacao-jogadorescalado-delete.jsp
	$(formDeleteJE).submit(function(e) {
		e.preventDefault();
		$.post( $(formDeleteJE).attr('action'),
				function(data, statusText, response) {
					if(response.status == 200) {
						alert('Jogador Retirado com sucesso!!!');
						window.opener.location.reload();
						window.close();
					} else {
						alert('ERRO:\n\n' + JSON.stringify(response));
					}
		}).fail(function(data) {
			alert('ERRO:\n\n' + data.responseText);
		});
				
	});	
	
	
	// utilizado em escalado-jogadorescalado-eventos-delete.jsp 
	$('#formDeleteEventoJE').submit(function(e) {
		e.preventDefault();
		$.post( $(formDeleteJE).attr('action'),
				{ 'collectionEventoId': $('#formDeleteEventoJE').find('select[name=collectionEventoId]').val() },
				function(data, statusText, response) {
					if(response.status == 200) { 
						alert('Evento Excluido com sucesso!!!');
						window.opener.location.reload();
						window.close();
					} else {
						alert('ERRO:\n\n' + JSON.stringify(response));
					}
		}).fail(function(data) {
			alert('ERRO:\n\n' + data.responseText);
		});		
	});
	
	
});