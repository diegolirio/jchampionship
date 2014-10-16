
$(function() {
	
	//$('#id_tabelaJ').hide();
	 
	
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
	
	function _GET(urlFull, paramName) {
		var url = urlFull.substring(urlFull.indexOf('?')+1, urlFull.length);
		var itens = url.split("&");
		for(n in itens) 
			if( itens[n].match(paramName) ) 
				return decodeURIComponent(itens[n].replace(paramName+"=", ""));
		return null;
	}	
	
	$('.saveTime').click(function(e) {
		e.preventDefault();		
		var pNome = form.find('input[name="nome"]').val();
		var pId = form.find('input[name="id"]').val();
		console.log(pNome);		
		$.post(	form.attr('action'),
				{ nome: pNome, id: pId },
				function(data, statusText, response) {
					if(response.status == 201) {
						console.log(JSON.stringify(response.responseJSON));
						form.find('input[name="id"]').val(response.responseJSON.id);
						$('#id_view').html(response.responseJSON.id);
						$('#id_tabelaJ').show();
						//alert(form.attr('action'));
						var paramPage = _GET(form.attr('action'), 'page');
						//alert(paramPage);
						if(paramPage == 'N') { // N=Normal
							window.location.reload();
						} else { // P=Page
							closeWindowPopupStatusNormal(response.status, 'Time Criado com sucesso', response.responseJSON.id, response.responseJSON.nome, 'id_times');
						}
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
	
	$('.addJogador').click(function() {
		
		var timeId = form.find('input[name="id"]').val();
		var jogadorId = $('#id_jogadores').val();
		
		$.post(	'/jchampionship/time/system/'+timeId+'/post/add/jogador/'+jogadorId,
				function(data, statusText, response) {
					
					alert('Time: ' + timeId + '\nJogador: ' + jogadorId);
					alert('Add jogador tabela!!!');
			
		}).fail(function(data) {
			alert(JSON.stringify(data));
		});
		
	});
	
	
});

