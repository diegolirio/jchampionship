$(function() {
	
	var popularCampeonatos = function() {
		$.getJSON('/jchampionship/campeonato/get/list', 
				  function(data) {
						$.each(data, function( i, c) {
							$('#id_campeonatos').append('<option value="'+c.id+'">'+c.descricao+'</option>');
						});
		          }
		);
	}
	
	popularCampeonatos();
	
});