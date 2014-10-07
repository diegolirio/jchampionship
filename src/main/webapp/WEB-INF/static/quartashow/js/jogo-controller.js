
$(function() {
	
	var formJogo = $('#id_form_jogo');
	
	var addTabelaJogos = function(jogos) {
		
	};
	
	$('.addJogo').click(function(e) {
		e.preventDefault();
		
		var timeA = formJogo.find('select[name="timeA.id"]').val();
		var grupoId = formJogo.find('select[name="grupo.id"]').val() ;
		
		console.log(timeA);
		
		$.post(	formEdicao.attr('action'),
				{ 'timeA.id': timeA,
				  'grupo.id': grupoId},
				function(data, statusText, response) {
					if(response.status == 201) {
						addTabelaJogos(data);					
					} else {
						alert("Not 201 ===> " + JSON.stringify(response));
					} 
				}
		).fail(function(data) {
			if(data.status == 401) {
				console.log(JSON.stringify(data));
				//if (data.responseJSON.errorMessages.edicao != undefined) alert("Erro ao cadastrar Grupo: Edicao " + data.responseJSON.errorMessages.edicao);
				$('#id_message_descricao').html(getHtmlErrorMessage(data.responseJSON.errorMessages.descricao));
				$('form span.tooltips').tooltip('show');				
			} 
			else {
				alert("Not 401 ===> " + JSON.stringify(data));
			}
		});
		
	});
	
	
	$('#id_excluir_grupo').click(function() {
		
			$.ajax({
				    url: formJogo.attr('action'),
				    type: 'DELETE',
				    success: function(data) {
				    	//...
				    	console.log(JSON.stringify(data));
				    	alert('Jogo Excluido com sucesso!!!');
				    	window.opener.location.reload();
				    	window.close(); 
				    }
			});		
	});
	
	$('#id_prox_jogos').click(function() {
		if($('#id_tbody').html().trim() != '') 
			return true;
		else {
			alert('Para prosseguir adicione pelo menos 1 grupo!');
			return false;
		}
	});
	
	
});

