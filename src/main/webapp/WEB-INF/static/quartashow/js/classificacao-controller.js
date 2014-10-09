
$(function() {
	
	var form = $('#id_form');
	
	var addTabelaClassificacao = function(classificacao) {
		var tbody = "";
		tbody += '<tr>';
		tbody += '	<td title="ID" class="text-muted">'+classificacao.id+'</td>';
		tbody += '	<td title="Colocação" class="text-info">'+classificacao.colocacao+'</td>';
		tbody += '	<td >'+classificacao.time.nome+'</td>';
		tbody += '	<td >'+classificacao.time.jogos+'</td>';
		tbody += '	<td >'+classificacao.time.vitorias+'</td>';
		tbody += '	<td >'+classificacao.time.empates+'</td>';
		tbody += '	<td >'+classificacao.time.derrotas+'</td>';
		tbody += '	<td >'+classificacao.time.pontos+'</td>';
		tbody += '	<td title="Excluir"><a href="/jchampionship/classificacao/delete_confirm/'+classificacao.id+'" onclick="showWindowPopup(this.href); return false;">Excluir</a></td>';
		tbody += '</tr>'; 
		$('#id_tbody_'+classificacao.grupo.id).append(tbody);		
	};
	
	$('.addClassificacao').click(function(e) {
		e.preventDefault();
		
		var grupo = form.find('select[name="grupo.id"]').val() ;
		var time = form.find('select[name="time.id"]').val() ;
		
		$.post(	form.attr('action'),
				{ 'grupo.id': grupo,
				  'time.id': time 
				},
				function(data, statusText, response) {
					if(response.status == 201) {
						addTabelaClassificacao(data);					
					} else {
						alert("Not 201 ===> " + JSON.stringify(response));
					} 
				}
		).fail(function(data) {
			if(data.status == 401) {
				console.log(JSON.stringify(data));
				//if (data.responseJSON.errorMessages.edicao != undefined) alert("Erro ao cadastrar Grupo: Edicao " + data.responseJSON.errorMessages.edicao);
				$('#id_message_time').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages['time.id']));
				$('#id_message_grupo').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages['grupo.id']));
				$('form span.tooltips').tooltip('show');				
			} 
			else {
				alert("Not 401 ===> " + JSON.stringify(data));
			}
		});
		
	});
	
	
	$('#id_excluir_classificacao').click(function() {
		
			$.ajax({
				    url: $('#formDeleteClassificacao').attr('action'),
				    type: 'DELETE',
				    success: function(data) {
				    	//...
				    	console.log(JSON.stringify(data));
				    	alert('Classificacao Excluido com sucesso!!!');
				    	window.opener.location.reload();
				    	window.close(); 
				    }
			});		
	});
	
	$('#id_prox_class').click(function() {
		if($('#id_tbody').html().trim() != '') 
			return true;
		else {
			alert('Para prosseguir adicione pelo menos 1 grupo!');
			return false;
		}
	});
	
	
});

