
$(function() {
	
	var form = $('#form');
	
	$(form).submit(function(e) {
		e.preventDefault();
		var id = form.find('input[name="id"]').val();	
		var nome = form.find('input[name="nome"]').val();	
		var posicaoId = form.find('select[name="posicao.id"]').val();
		var uriFoto = form.find('input[name=uriFoto]').val();
		var btnSave = form.find('input[name=btnSave]').val();
		console.log(nome);		
		$.post(	form.attr('action'),
				{ id: id,
			      nome: nome,
				  'posicao.id': posicaoId, 
				  uriFoto: uriFoto }, 
				function(data, statusText, response) {
					if(response.status == 201) {
						//console.log(JSON.stringify(response.responseJSON));
						if (_GET("page") == 'popup') {
							closeWindowPopupStatusNormal(response.status, 'Jogador Criado com sucesso', response.responseJSON.id, response.responseJSON.nome, 'id_jogadores');
						} else {
							if(btnSave != 'Salvar') { // Savar e Cadastrando um Novo...
								window.location.href = '/jchampionship/jogador/system/form/0';	
							} else {
								var _get =_GET("next");
								if(_get == '' || _get == undefined)
									_get = '/jchampion'+response.getResponseHeader('Location');
								window.location.href = _get;
							}
						}
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

