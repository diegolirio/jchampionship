
$(function() {
	
	var formJogo = $('#id_form_jogo');
	
	var addTabelaJogos = function(jogo) {
		var html = "<tr class='text-center'>";
		//html += "<td>"+jogo.id+"</td>"; 
		html += "<td>"+jogo.rodada+"</td>";
		html += "<td >"+jogo.grupo.descricao+"</td>";
		html += "<td >"+jogo.timeA.nome+"</td>";
		html += "<td >"+jogo.resultadoA+"</td>";
		html += "<td class='text-center text-muted'>X</td>";
		html += "<td >"+jogo.resultadoB+"</td>";
		html += "<td >"+jogo.timeB.nome+"</td>";
		html += "<td >"+jogo.local.descricao+"</td>";
		html += "<td >"+jogo.harbito.nome+"</td>";
		html += "<td>"+jogo.dataHora+"</td>";
		html += "<td><a href='/jchampionship/jogo/delete_confirm/"+jogo.id+"' onclick='showWindowPopup(this.href); return false;'>Excluir</a></td>";
		html += "</tr>";		
		$('#id_tbody').append(html);
	};
	
	$('#id_form_jogo').submit(function(e) {
		
		e.preventDefault();
		
		// hidden
		var id = formJogo.find('input[name="id"]').val();
		var statusId = formJogo.find('input[name="status.id"]').val();
		
		var timeA = formJogo.find('select[name="timeA.id"]').val();
		var timeB = formJogo.find('select[name="timeB.id"]').val();
		var harbito = formJogo.find('select[name="harbito.id"]').val();
		var local = formJogo.find('select[name="local.id"]').val();
		var grupoId = formJogo.find('select[name="grupo.id"]').val();
		var dataHora = formJogo.find('input[name=dataHora]').val();
		var rodada = formJogo.find('input[name=rodada]').val();
		
		$.post(	formJogo.attr('action'),
				{ 'id': (id == '' ? 0 : id),
				  'status.id': (statusId == '' ? 1 : statusId),
				  'timeA.id': timeA,
				  'timeB.id': timeB,
				  'harbito.id': harbito,
				  'local.id': local,
				  'grupo.id': grupoId,
				  'dataHora': dataHora,
				  'rodada':   rodada}, 
				function(data, statusText, response) {
					if(response.status == 201) {
						//console.log(JSON.stringify(data));
						if(id > 0)
							window.location.href = '/jchampionship/edicao/'+formJogo.find('input[name="grupo.edicao.id"]').val()+"#jogo"+id;
						else
							addTabelaJogos(data);					
					} else {
						alert("OK ===> status: " + response.status + "\n\n" + JSON.stringify(response));
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
				$('#id_message_rodada').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.rodada));
				$('form span.tooltips').tooltip('show');				
			} 
			else {
				alert("Falha ===> " + JSON.stringify(data));
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
		var ok = confirm('Deveja Finalizar ?\nJogo sera finalizado, e calculo da classificacao e artilharia serao gerados !');
		if (ok == true) {
			$.post( $(this).attr('href'),
					function(data, statusText, response) {
						console.log(JSON.stringify(response));
						//alert(JSON.stringify(response));
						window.location.href = '/jchampionship' + response.getResponseHeader('Location');
			}).fail(function(data) {
				console.log(JSON.stringify(data));
				alert(JSON.stringify(data));
			});
		}
	});

	$('#idVoltarStatusJogo').click(function(e) {
		e.preventDefault();
		var ok = confirm("Deseja voltar situação do Jogo ?\nCalculo da clasificação e artilharia será cancelado.");
		if(ok == true) {
			$.post( $(this).attr('href'), 
					function(data, statusText, response) {
						//alert(response.status); 
						if(response.status == 200) {
							window.location.reload();
						} else {
							alert('Erro: ' + JSON.stringify(response));
						}
			}).fail(function(data) {
				alert('Erro: ' + JSON.stringify(data));
			});
		}
	});
	
});

