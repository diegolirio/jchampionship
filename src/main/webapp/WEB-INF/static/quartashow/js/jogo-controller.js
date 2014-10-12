
$(function() {
	
	var formJogo = $('#id_form_jogo');
	
	var addTabelaJogos = function(jogo) {
		var html = "<tr>";
		html += "<td>"+jogo.id+"</td>"; 
		html += "<td >"+jogo.grupo.descricao+"</td>";
		html += "<td >+"jogo.timeA.nome+"</td>";
		html += "<td >"+jogo.resultadoA+"</td>";
		html += "<td class='text-center text-muted'>X</td>";
		html += "<td >"+jogo.resultadoB+"</td>";
		html += "<td >"+jogo.timeB.nome+"</td>";
		html += "<td >"+jogo.local.descricao+"</td>";
		html += "<td >+"jogo.harbito.nome+"</td>";
		html += "<td><a href='/jchampionship/jogo/delete_confirm/"+jogo.id+" onclick='showWindowPopup(this.href); return false;'>Excluir</a></td>";
		html += "</tr>";		
		$('#id_tbody').append(html);
	};
	
	$('.addJogo').click(function(e) {
		e.preventDefault();
		
		var timeA = formJogo.find('select[name="timeA.id"]').val();
		var timeB = formJogo.find('select[name="timeB.id"]').val();
		var harbito = formJogo.find('select[name="harbito.id"]').val();
		var local = formJogo.find('select[name="local.id"]').val();
		var grupoId = formJogo.find('select[name="grupo.id"]').val() ;
		
		console.log(timeA);
		
		$.post(	formJogo.attr('action'),
				{ 'timeA.id': timeA,
				  'timeB.id': timeB,
				  'harbito.id': harbito,
				  'local.id': local,
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
				$('#id_message_grupo').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.grupo));
				$('#id_message_harbito').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.harbito));
				$('#id_message_local').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.local));
				$('#id_message_timea').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.timeA));
				$('#id_message_timeb').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.timeB));
				$('#id_message_resultadoa').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.resultadoA));
				$('#id_message_resultadob').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.resultadoB));
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

