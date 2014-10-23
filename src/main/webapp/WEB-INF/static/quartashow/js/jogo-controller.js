
$(function() {
	
	var formJogo = $('#id_form_jogo');
	
	var addTabelaJogos = function(jogo) {
		var html = "<tr>";
		html += "<td>"+jogo.id+"</td>"; 
		html += "<td >"+jogo.grupo.descricao+"</td>";
		html += "<td >"+jogo.timeA.nome+"</td>";
		html += "<td >"+jogo.resultadoA+"</td>";
		html += "<td class='text-center text-muted'>X</td>";
		html += "<td >"+jogo.resultadoB+"</td>";
		html += "<td >"+jogo.timeB.nome+"</td>";
		html += "<td >"+jogo.local.descricao+"</td>";
		html += "<td >"+jogo.harbito.nome+"</td>";
		html += "<td><a href='/jchampionship/jogo/delete_confirm/"+jogo.id+"' onclick='showWindowPopup(this.href); return false;'>Excluir</a></td>";
		html += "</tr>";		
		$('#id_tbody').append(html);
	};
	
	$('#id_form_jogo').submit(function(e) {
		
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
				  'grupo.id': grupoId },
				function(data, statusText, response) {
					if(response.status == 201) {
						console.log(JSON.stringify(data));
						addTabelaJogos(data);					
					} else {
						alert("Not 201 ===> " + JSON.stringify(response));
					}
				}
		).fail(function(data) {
			if(data.status == 401) {
				console.log(JSON.stringify(data));
				//if (data.responseJSON.errorMessages.edicao != undefined) alert("Erro ao cadastrar Grupo: Edicao " + data.responseJSON.errorMessages.edicao);
				$('#id_message_grupo').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages['grupo.id']));
				$('#id_message_harbito').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages['harbito.id']));
				$('#id_message_local').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages['local.id']));
				$('#id_message_timea').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages['timeA.id']));
				$('#id_message_timeb').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages['timeB.id']));
				$('#id_message_resultadoa').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.resultadoA));
				$('#id_message_resultadob').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.resultadoB));
				$('form span.tooltips').tooltip('show');				
			} 
			else {
				alert("Not 401 ===> " + JSON.stringify(data));
			}
		});
		
	});
	
	
	$('#formDelete').submit(function(e) {
		
			e.preventDefault();
		
			$.ajax({
				    url: $('#formDelete').attr('action'),
				    type: 'DELETE',
				    success: function(data) {
				    	//...
				    	console.log(JSON.stringify(data));
				    	alert('Jogo Excluido com sucesso!!!');
				    	window.opener.location.reload();
				    	window.close(); 
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown) { 
				        alert("Status: " + textStatus + "\n\nError: " + errorThrown); 
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
	
	
	/********************************************************************
	 * Adiciona informacoes da partida (Escalacao) 
	 ********************************************************************/
	$('#id_add_escalacao_jogo').click(function(e) {
		
		e.preventDefault();
		
		var ok = confirm("Deseja realmente adicinar as informacoes a partida ?\nPartida recebera uma escalacao com jogadores de ambos os times.");
		if(ok == true) {
			$.post( $(this).attr('href'),
					function(data, statusText, response) {
						console.log(JSON.stringify(response));
						window.location.href = '/jchampionship' + response.getResponseHeader('Location');
			}).fail(function(data) {
				//console(JSON.stringify(data));
				alert(data.responseText);
			});
		}
		
	});
	
	$('#id_finalizar_jogo').click(function(e) {
		e.preventDefault();
		alert('Finalizar devera Setar o jogo para finalizado, e calcular a classificacao e artilharia!!!!');
		$.post( $(this).attr('href'),
				function(data, statusText, response) {
					console.log(JSON.stringify(response));
					//alert(JSON.stringify(response));
					window.location.href = '/jchampionship' + response.getResponseHeader('Location');
		}).fail(function(data) {
			console.log(JSON.stringify(data));
			alert(JSON.stringify(data));
		});
	});
	
});

