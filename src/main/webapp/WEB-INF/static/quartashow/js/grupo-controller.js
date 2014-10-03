
function getGrupos(edicaoId) {
	$.getJSON('/jchampionship/grupo/get/list/by/edicao/'+edicaoId, function(data) {
		var tbody = "";
		$.each(data, function(i, g) {
			tbody += '<tr>';
			tbody += '   <td title="ID">'+g.id+'</td>';
			tbody += '   <td >'+g.descricao+'</td>';
			tbody += '	 <td title="Excluir"><a href="#">Excluir</a></td>';
			tbody += '</tr>';			
		});
		$('#id_tbody').html(tbody);
		
	});
}

$(function() {

	var formGrupo = $('#id_form_grupo');
	
	$('.addGrupo').click(function(e) {
		e.preventDefault();
		
		$.post(	formGrupo.attr('action'),
				{ descricao: formGrupo.find('input[name=descricao]').val() },
				function(data, statusText, response) {
					
					alert(JSON.stringify(data));
					
					// TODO: inserir tratar validacao
					// TODO: popula na tabela
					
				}
		).fail(function(data, statusText, response) {
			alert(JSON.stringify(data));
		});
		
	});
	
});

