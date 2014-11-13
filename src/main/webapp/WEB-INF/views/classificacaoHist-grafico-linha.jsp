<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
<!--     <script language="javascript" type="text/javascript" src="jquery.js"></script> -->
<!--     <script language="javascript" type="text/javascript" src="jquery.flot.js"></script>	 -->
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/flot_charts/jquery.flot.js"></script>
	
	<h3 class="text-info">Histórico de Colocação</h3>
	
	<p id="choices"></p>
	<br/><br/>
	<div id="placeholder" style="width:100%;height:300px;"></div>
	
	<script type="text/javascript">
	


		function sleep(milliseconds) {
		  var start = new Date().getTime();
		  for (var i = 0; i < 1e7; i++) {
		    if ((new Date().getTime() - start) > milliseconds){
		      break;
		    }
		  }
		}
	
	
	
	$(function () {
	    
		var times = [];
		var classhist = [];
		
	    // insert checkboxes 
	    var choiceContainer = $("#choices");
		
		// pega os times
		$.getJSON(  '${pageContext.request.contextPath}/time/get/list/by/edicao/${edicao.id}/json',
					function(data) {
						times = data;
						console.log(JSON.stringify(times));
						
					    $.each(times, function(key, val) { 
					        choiceContainer.append('<div class="col-md-2"><input type="checkbox" title="'+val.nome+'" name="' + val.id +
					                               '" checked="checked" id="id' + val.id + '">' +
					                               '<label class="text-muted" for="id' + val.id + '">'
					                                + val.nome + '</label></div>');
					    });
					    choiceContainer.find("input").click(plotAccordingToChoices);						
						
						// pega os grupos
						$.getJSON(  '${pageContext.request.contextPath}/grupo/get/list/by/edicao/${edicao.id}',
									function(dataGrupos) {
										console.log(JSON.stringify(dataGrupos));
										console.log(dataGrupos.length);
										$.each(dataGrupos, function(i, g) {
											console.log(i); 
											// a pega a classificacao de cada grupo
											$.getJSON(  '${pageContext.request.contextPath}/classificacaoHist/get/list/by/grupo/'+g.id+'/json',
													function(dataHist) { 
														classhist.push(dataHist);
														console.log(JSON.stringify(classhist));
														if(i == dataGrupos.length-1) {
															plotAccordingToChoices();
														}
													}
											);												
										});
										
										
									}
						);
					}
		);
		
		//times = [{"id":1,"nome":"Corinthinas"}, {"id":2,"nome":"Santos"}];
		//classhist = [{"id":1,"rodada":1,"time":{"id":1,"nome":"Corinthinas"},"colocacao":1,"pontos":3,"vitorias":1,"derrotas":0,"empates":0,"jogos":1,"golsPro":1,"golsContra":0,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":2,"rodada":1,"time":{"id":2,"nome":"Santos"},"colocacao":2,"pontos":0,"vitorias":0,"derrotas":1,"empates":0,"jogos":1,"golsPro":0,"golsContra":1,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":5,"rodada":2,"time":{"id":2,"nome":"Santos"},"colocacao":1,"pontos":3,"vitorias":1,"derrotas":1,"empates":0,"jogos":2,"golsPro":2,"golsContra":1,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":6,"rodada":2,"time":{"id":1,"nome":"Corinthinas"},"colocacao":2,"pontos":3,"vitorias":1,"derrotas":1,"empates":0,"jogos":2,"golsPro":1,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":7,"rodada":3,"time":{"id":2,"nome":"Santos"},"colocacao":1,"pontos":4,"vitorias":1,"derrotas":1,"empates":1,"jogos":3,"golsPro":2,"golsContra":1,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":8,"rodada":3,"time":{"id":1,"nome":"Corinthinas"},"colocacao":2,"pontos":4,"vitorias":1,"derrotas":1,"empates":1,"jogos":3,"golsPro":1,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":11,"rodada":4,"time":{"id":1,"nome":"Corinthinas"},"colocacao":1,"pontos":7,"vitorias":2,"derrotas":1,"empates":1,"jogos":4,"golsPro":2,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":12,"rodada":4,"time":{"id":2,"nome":"Santos"},"colocacao":2,"pontos":4,"vitorias":1,"derrotas":2,"empates":1,"jogos":4,"golsPro":2,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":13,"rodada":5,"time":{"id":2,"nome":"Santos"},"colocacao":1,"pontos":7,"vitorias":2,"derrotas":2,"empates":1,"jogos":5,"golsPro":3,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":14,"rodada":5,"time":{"id":1,"nome":"Corinthinas"},"colocacao":2,"pontos":7,"vitorias":2,"derrotas":2,"empates":1,"jogos":5,"golsPro":2,"golsContra":3,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"}];
	
	    // hard-code color indices to prevent them from shifting as
	    // countries are turned on/off
	    var i = 0;
	    $.each(times, function(key, val) {
	        val.color = i;
	        ++i;
	    });
	    
	    function plotAccordingToChoices() {
	    	//sleep(500);
	        var timesData = [];
	        
	        choiceContainer.find("input:checked").each(function () {
	            var timeId = $(this).attr("name");
				var tData = {label: $(this).attr("title"), data:[]};
				$.each(classhist[0], function(i, ch) {
					if(timeId == ch.time.id) {
						tData.data.push([ch.rodada, Number(ch.colocacao)]); 
					}
				});
				timesData.push(tData);
	        });
	        
	        if (timesData.length > 0)
			
				var colocacoesArray = function() {
					var colsArray = [];
					for(var i = 0; i <= times.length; i++) 
						colsArray[i] = [i+1, (i+1)+'º'];
					return colsArray;			
				};
			
				var rodadasArray = function() {
					var rArray = [];
					var qtdeRodada = 0;
					$.each(classhist[0], function(i, ch) {
						if(ch.rodada > qtdeRodada)
							qtdeRodada = ch.rodada;
					});
					for(var i = 0; i <= qtdeRodada; i++) {
						rArray[i] = [i+1, "Rodada " + (i+1)];
					} 
					return rArray;			
				};				
				
				var options = { 
								yaxis: {min: 0, 
										max: times.length+1,
									    ticks: colocacoesArray,
										transform: function (v) { return -v; },
										//inverseTransform: function (v) { return -v; }										   
									},						
								xaxis: { tickDecimals: 0,
										 ticks: rodadasArray},
								legend: {show: true},
								lines: { show: true},
							};
			
	            $.plot( $("#placeholder"), timesData, options);
	    }
	
	});
	</script> 