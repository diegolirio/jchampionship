
$(function() {

	var idComponent = "id_times";
	
	idComponent = _GET("idSelected");
	
	if (idComponent == undefined || idComponent == '')
		idComponent = "id_times";
	
	$('#id_tabelaJ').hide();
	 
	
	var form = $('#form');
	
//	var addTabelaJogadores = function(jogador) {
//		var tbody = "";
//		tbody += '<tr>';
//		tbody += '	<td title="ID">'+jogador.id+'</td>';
//		tbody += '	<td >'+jogador.nome+'</td>';
//		tbody += '	<td title="Excluir"><a href="/jchampionship/jogador/delete_confirm/'+jogador.id+'" onclick="showWindowPopup(this.href); return false;">Excluir</a></td>';
//		tbody += '</tr>'; 
//		$('#id_tbody').append(tbody);		
//		form.find('input[name=descricao]').val('');
//	};	
	
	$('.saveTime').click(function(e) {
		e.preventDefault();		
		var pNome = form.find('input[name="nome"]').val();		
		console.log(pNome);		
		$.post(	form.attr('action'),
				{ nome: pNome },
				function(data, statusText, response) {
					if(response.status == 201) {
						console.log(JSON.stringify(response.responseJSON));
						form.find('input[name="id"]').val(response.responseJSON.id);
						$('#id_view').html(response.responseJSON.id);
						$('#id_tabelaJ').show();
						closeWindowPopupStatusNormal(response.status, 'Time Criado com sucesso', response.responseJSON.id, response.responseJSON.nome, idComponent);
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

