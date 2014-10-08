
$(function() {
	
	var form = $('#formLocal');
	
	$('.saveLocal').click(function(e) {
		e.preventDefault();		
		var pDescricao = form.find('input[name="descricao"]').val();		
		console.log(pDescricao);		
		$.post(	form.attr('action'),
				{ descricao: pDescricao },
				function(data, statusText, response) {
					if(response.status == 201) {
						console.log(JSON.stringify(response.responseJSON));
						closeWindowPopupStatusNormal(response.status, 'Local Criado com sucesso', response.responseJSON.id, response.responseJSON.descricao, 'id_locais');
					} else {
						alert("Not 201 ===> " + JSON.stringify(response));
					} 
				}
		).fail(function(data) {
			if(data.status == 401) { 
				console.log(JSON.stringify(data));
				$('#id_message_descricao').html(getHtmlTooltipsErrorMessage(data.responseJSON.errorMessages.descricao));
				$('form span.tooltips').tooltip('show');				
			} 
			else {
				alert("Not 401 ===> " + JSON.stringify(data));
			}
		});		
	});
	
	
});

