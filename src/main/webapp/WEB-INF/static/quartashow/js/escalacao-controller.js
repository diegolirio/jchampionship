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
				function() {
					//alert(JSON.stringify(data));
					window.opener.location.reload();
					window.close();
				}
		).fail(function(data) {
			//console.log(data.responseText);
			alert(data.responseText); 
		});
	});
	
});