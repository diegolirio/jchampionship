
$(function() {
	
	var form = $('#form');
	
	$('.savejogador').click(function(e) {
		e.preventDefault();		
		var nome = form.find('input[name="nome"]').val();		
		console.log(nome);		
		$.post(	form.attr('action'),
				{ nome: nome },
				function(data, statusText, response) {
					if(response.status == 201) {
						console.log(JSON.stringify(response.responseJSON));
						closeWindowPopupStatusNormal(response.status, 'Jogador Criado com sucesso', response.responseJSON.id, response.responseJSON.nome, 'id_jogadores');
					} else {
						alert("Not 201 ===> " + JSON.stringify(response));
					} 
				}
		).fail(function(data) {
			if(data.status == 401) { 
				console.log(JSON.stringify(data));
				$('#id_message_nome').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.nome));
				$('form span.tooltips').tooltip('show');				
			} 
			else {
				alert("Not 401 ===> " + JSON.stringify(data));
			}
		});		
	});
	
	
});

