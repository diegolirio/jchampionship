

function getEdicoesPendentes() {
	$.getJSON("/jchampionship/edicao/get/list/by/status/1", function(data) {
		var tbody = "";
		$.each(data, function(i, e) {
			tbody += '<tr">';
			tbody += '   <td title="ID">'+e.id+'</td>';
			tbody += '   <td >'+e.descricao+'</td>';
			tbody += '   <td title="Campeonato">'+e.campeonato.descricao+'</td>';
			tbody += '   <td title="Pendente">'+e.status.descricao+'</td>';
			tbody += '	 <td title="Concluir">';
			tbody += '		<a href="/jchampionship/edicao/system/'+e.id+'/grupos">concluir </a>';
			tbody += '		<a href="/jchampionship/edicao/delete/'+e.id+'" id="idDeleteEdicao" class="text-danger"> excluir</a>';
			tbody += '</td>';
			tbody += '</tr>';			
		});
		$('#id_tbody').html(tbody);
		
	});
}

$(function() {

	var formEdicao = $('#id_form_edicao');

	var getHtmlErrorMessage = function(message) {
		if (message == '' || message == undefined)
			return '';
		return '<span data-placement="right" data-original-title="'
				+ message
				+ '"  class="glyphicon glyphicon-warning-sign text-danger tooltips" ></span>';

	};

	$('.btnFormEdicaoSave').click(
			function(e) {
				e.preventDefault();
				$('form span.tooltips').tooltip('hide');
				$.post(	formEdicao.attr('action'),
						{
						  descricao : formEdicao.find("input[name='descricao']").val(),
						 'campeonato.id' : formEdicao.find("select[name='campeonato.id']").val()
						},
						function(data, statusText, headers) {
							//  ...
						})
						.fail(function(data, statusText, headers) {
							if (data.status == 201) {
								window.location.href = '/jchampionship'+ data.getResponseHeader("Location");
							} else
							if (data.status == 401) {
								$('#id_message_descricao').html(getHtmlErrorMessage(data.responseJSON.errorMessages.descricao));
								$('#id_message_campeonatoId').html(getHtmlErrorMessage(data.responseJSON.errorMessages['campeonato.id']));
								$('form span.tooltips').tooltip('show');
							} else {
								alert("Erro: " + JSON.stringify(data));
							}
						});
	 });
	
	
	$('#id_concluir_edicao').click(function() {
		
		$.post( $('#id_concluir_edicao').attr('href').replace('#', ''),
				function(data, statusText, response) {
					alert(JSON.stringify(response));
					window.location.href = '/jchampionship/'+response.getResponseHeader('Location');
		}).fail(function(data) {
			alert(JSON.stringify(data));
			return false;
		});		
		
	});
	
	$('#idDeleteEdicao').click(function(e) {
		e.preventDefault();
		$.post( $(this).attr('href'),
				function(data, statusText, response) {
					if (response.status == 200) {
						window.location.reload();
					} else { 
						alert(JSON.stringify(response));
					}
		}).fail(function(data) {
			alert(JSON.stringify(data));
		});	
	});

	$('#idFinalizarFase').click(function(e) {
		e.preventDefault();
		//alert($(this).attr('href')); 
		$.post( $(this).attr('href'),
				function(data, statusText, response) {
					if(response.status == 200)
						window.location.href = response.getResponseHeader('Location');
					else
						alert(JSON.stringify(response));
		}).fail(function(data) {
			alert(JSON.stringify(data));
		});
	});
	
});

