<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
    <script language="javascript" type="text/javascript" src="jquery.js"></script>
    <script language="javascript" type="text/javascript" src="jquery.flot.js"></script>	
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/flot_charts/jquery.flot.js"></script>
	
	<h3>Histórico de Colocação</h3>
	<p id="choices"></p>
	<div id="placeholder" style="width:100%;height:300px;"></div>
	
	<script type="text/javascript">
	$(function () {
	    
		var times = [{"id":1,"nome":"Corinthinas"}, {"id":2,"nome":"Santos"}];
		var classhist = [{"id":1,"rodada":1,"time":{"id":1,"nome":"Corinthinas"},"colocacao":1,"pontos":3,"vitorias":1,"derrotas":0,"empates":0,"jogos":1,"golsPro":1,"golsContra":0,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":2,"rodada":1,"time":{"id":2,"nome":"Santos"},"colocacao":2,"pontos":0,"vitorias":0,"derrotas":1,"empates":0,"jogos":1,"golsPro":0,"golsContra":1,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":5,"rodada":2,"time":{"id":2,"nome":"Santos"},"colocacao":1,"pontos":3,"vitorias":1,"derrotas":1,"empates":0,"jogos":2,"golsPro":2,"golsContra":1,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":6,"rodada":2,"time":{"id":1,"nome":"Corinthinas"},"colocacao":2,"pontos":3,"vitorias":1,"derrotas":1,"empates":0,"jogos":2,"golsPro":1,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":7,"rodada":3,"time":{"id":2,"nome":"Santos"},"colocacao":1,"pontos":4,"vitorias":1,"derrotas":1,"empates":1,"jogos":3,"golsPro":2,"golsContra":1,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":8,"rodada":3,"time":{"id":1,"nome":"Corinthinas"},"colocacao":2,"pontos":4,"vitorias":1,"derrotas":1,"empates":1,"jogos":3,"golsPro":1,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":11,"rodada":4,"time":{"id":1,"nome":"Corinthinas"},"colocacao":1,"pontos":7,"vitorias":2,"derrotas":1,"empates":1,"jogos":4,"golsPro":2,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":12,"rodada":4,"time":{"id":2,"nome":"Santos"},"colocacao":2,"pontos":4,"vitorias":1,"derrotas":2,"empates":1,"jogos":4,"golsPro":2,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":13,"rodada":5,"time":{"id":2,"nome":"Santos"},"colocacao":1,"pontos":7,"vitorias":2,"derrotas":2,"empates":1,"jogos":5,"golsPro":3,"golsContra":2,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"},{"id":14,"rodada":5,"time":{"id":1,"nome":"Corinthinas"},"colocacao":2,"pontos":7,"vitorias":2,"derrotas":2,"empates":1,"jogos":5,"golsPro":2,"golsContra":3,"grupo":{"id":1,"descricao":"A","edicao":{"id":1,"descricao":"2014","status":{"id":2,"descricao":"Em Andamento","imgName":"bola_32.png"},"campeonato":{"id":1,"descricao":"Quarta Show","imgName":"/static/quartashow/img/trofeus/trophy1.png"}}},"observacao":"N"}];
	
	    // hard-code color indices to prevent them from shifting as
	    // countries are turned on/off
	    var i = 0;
	    $.each(times, function(key, val) {
	        val.color = i;
	        ++i;
	    });
	    
	    // insert checkboxes 
	    var choiceContainer = $("#choices");
		
	    $.each(times, function(key, val) {
	        choiceContainer.append('<br/><input type="checkbox" title="'+val.nome+'" name="' + val.id +
	                               '" checked="checked" id="id' + val.id + '">' +
	                               '<label for="id' + val.id + '">'
	                                + val.nome + '</label>');
	    });
	    choiceContainer.find("input").click(plotAccordingToChoices);
	
	    
	    function plotAccordingToChoices() {
	        var timesData = [];
	        choiceContainer.find("input:checked").each(function () {
	            var timeId = $(this).attr("name");
				var tData = {label: $(this).attr("title"), data:[]};
				$.each(classhist, function(i, ch) {
					if(timeId == ch.time.id) {
						tData.data.push([ch.rodada, Number(ch.colocacao)]); 
					}
				});
				timesData.push(tData);
	        });
	        if (timesData.length > 0)
			
				function colocacoesArray() {
					var colsArray = []
					for(var i = 0; i <= times.length; i++) 
						colsArray[i] = i+1;
					return colsArray;			
				}
			
				var options = { 
								yaxis: {min: 0, 
										max: times.length+1,
									    ticks: colocacoesArray,
										transform: function (v) { return -v; },
										//inverseTransform: function (v) { return -v; }										   
									},						
								xaxis: { tickDecimals: 0 },
								legend: {show: true},
								lines: { show: true},
							}
			
	            $.plot( $("#placeholder"), timesData, options);
	    }
	
	    plotAccordingToChoices();
	});
	</script> 