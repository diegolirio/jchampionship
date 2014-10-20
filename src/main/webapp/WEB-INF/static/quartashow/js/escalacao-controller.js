$(function() {
	
	var form = $('#formJE');
	
	$(form).submit(function(e) {
		e.preventDefault();
		var jogadorEscalado = form.find('select[name="jogadorEscalado.id"]').val();
		if(jogadorEscalado == '') {
			alert('Selecione o Jogador');
			return false;
		}
		$.post( $(form).attr('action'), 
				{ jogadorEscaladoId: jogadorEscalado },
				function(data) {
					alert(JSON.stringify(data));
				}
		).fail(function(data) {
			alert(JSON.stringify(data));
		});
	});
	
});