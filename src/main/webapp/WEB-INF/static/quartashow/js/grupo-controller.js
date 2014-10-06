
//function getGrupos(edicaoId) {
//	$.getJSON('/jchampionship/grupo/get/list/by/edicao/'+edicaoId, function(data) {
//		var tbody = "";
//		$.each(data, function(i, g) {
//			tbody += '<tr>';
//			tbody += '   <td title="ID">'+g.id+'</td>';
//			tbody += '   <td >'+g.descricao+'</td>';
//			tbody += '	 <td title="Excluir"><a href="/jchampionship/grupo/delete/'+g.id+'">Excluir</a></td>';
//			tbody += '</tr>';			
//		});
//		$('#id_tbody').html(tbody);
//	});
//}

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
		tbody += '	<td title="Excluir"><a href="/jchampionship/grupo/delete_confirm/'+grupo.id+'">Excluir</a></td>';
		tbody += '</tr>'; 
		$('#id_tbody').append(tbody);		
		formGrupo.find('input[name=descricao]').val('');
	};
	
	$('.addGrupo').click(function(e) {
		e.preventDefault();
		
		$.post(	formGrupo.attr('action'),
				{ descricao:   formGrupo.find('input[name=descricao]').val(),
				  'edicao.id': formGrupo.find('input[name="edicao.id"]').val() },
				function(data, statusText, response) {
					if(response.status == 201) {
						addTabelaGrupo(data); // data fake					
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
	
});

