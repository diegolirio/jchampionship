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
});