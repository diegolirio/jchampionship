
$(function() {
	
	var form = $('#form');
	
	$('.saveJogador').click(function(e) {
		e.preventDefault();		
		var nome = form.find('input[name="nome"]').val();	
		var posicaoId = form.find('select[name="posicao.id"]').val();
		var uriFoto = form.find('input[name=uriFoto]').val();
		alert(uriFoto);
		console.log(nome);		
		$.post(	form.attr('action'),
				{ nome: nome,
				  'posicao.id': posicaoId, 
				  uriFoto: uriFoto }, 
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
				$('#id_message_posicao').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages['posicao.id']));
				$('form span.tooltips').tooltip('show');				
			} 
			else {
				alert("Not 401 ===> " + JSON.stringify(data));
			}
		});		
	});
	
	
});

