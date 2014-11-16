
$(function() {
	
	var formGrupo = $('#id_form_grupo');
	
	var getHtmlErrorMessage = function(message) {
		if (message == '' || message == undefined)
			return '';
		return '<span data-placement="right" data-original-title="'
				+ message
				+ '"  class="glyphicon glyphicon-warning-sign text-danger tooltips" ></span>';

	};
	
	var addTabelaGrupo = function(grupo) {
		// TODO: retornar json do incluido
		var tbody = "";
		tbody += '<tr>';
		tbody += '	<td title="ID">'+grupo.id+'</td>';
		tbody += '	<td >'+grupo.descricao+'</td>';
		tbody += '	<td title="Excluir"><a href="/jchampionship/grupo/delete_confirm/'+grupo.id+'" onclick="showWindowPopup(this.href); return false;">Excluir</a></td>';
		tbody += '</tr>'; 
		$('#id_tbody').append(tbody);		
		formGrupo.find('input[name=descricao]').val('');
	};
	
	$('.addGrupo').click(function(e) {
		e.preventDefault();
		
		var edicao = formGrupo.find('input[name="edicao.id"]').val() ;
		console.log(edicao);
		
		$.post(	formGrupo.attr('action'),
				{ descricao:   formGrupo.find('input[name=descricao]').val(),
				  'edicao.id': edicao,
				  'status.id': 1,
				  'fase.id': 1},
				function(data, statusText, response) {
					if(response.status == 201) {
						addTabelaGrupo(data);					
					} else {
						alert("Not 201 ===> " + JSON.stringify(response));
					} 
				}
		).fail(function(data) {
			if(data.status == 401) {
				console.log(JSON.stringify(data));
				if (data.responseJSON.errorMessages.edicao != undefined) alert("Erro ao cadastrar Grupo: Edicao " + data.responseJSON.errorMessages.edicao);
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
				    url: $('#formDeleteGrupo').attr('action'),
				    type: 'DELETE',
				    success: function(data) {
				    	//...
				    	console.log(JSON.stringify(data));
				    	alert('Grupo Excluido com sucesso!!!');
				    	window.opener.location.reload();
				    	window.close(); 
				    }
			});		
	});
	
	$('#id_prox_gpos').click(function() {
		if($('#id_tbody').html().trim() != '') 
			return true;
		else {
			alert('Para prosseguir adicione pelo menos 1 grupo!');
			return false;
		}
	});
	
	
});

