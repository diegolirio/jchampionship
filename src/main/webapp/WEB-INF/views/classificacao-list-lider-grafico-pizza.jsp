<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<div class="col-lg-3 col-md-3">
		<div class="demo-container">
			<p id="id_lider" class="text-center"></p>
			<div id="placeholder_pie" class="demo-placeholder"></div>
		</div>
	</div>
	     
	<link href="${pageContext.request.contextPath}/static/flot_charts/examples.css" rel="stylesheet" type="text/css">
	<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="../../excanvas.min.js"></script><![endif]-->
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/flot_charts/jquery.flot.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/flot_charts/jquery.flot.pie.js"></script>
	
	<script type="text/javascript">
	
			$(function() {  

				var placeholder = $("#placeholder_pie");
				 
				var getDataGrafico = function(classificacaoJSON) {
					var data = [
								{ label: classificacaoJSON.vitorias + " Vitoria(s)", data: classificacaoJSON.vitorias, color: "#298A08"},
								{ label: classificacaoJSON.empates  + " Empate(s)",  data: classificacaoJSON.empates,  color: "#FACC2E"},
								{ label: classificacaoJSON.derrotas + " Derrota(s)", data: classificacaoJSON.derrotas, color: "#B40404"}
							];
					return data;
				};
				
 				var loadChartPie = function(placelider, data) {
 					placelider.unbind();
					$.plot(placelider, data, {  
						series: {
							pie: { 
								show: true,
								tilt: 0.5,
								combine: {
									color: "#999",
									threshold: 0.1
								}
							}
						},
						legend: {
							show: false
						}
					});					

 				};
 				
				// get lista de grupos 
				$.getJSON(  '${pageContext.request.contextPath}/grupo/get/list/by/edicao/${edicao.id}',
						  function(dataGrupos) {
								$.each(dataGrupos, function(i, g) {
									$.getJSON('${pageContext.request.contextPath}/classificacao/get/list/lider/by/grupo/'+g.id,
											  function(dataLideres) {
													//alert(JSON.stringify(dataLider));
													$.each(dataLideres, function(i, lider) {
														var dataGrafico = getDataGrafico(lider);
// 														$(placeholder).html( $(placeholder).html() +  
// 																			 "<p>"+lider.grupo.descricao+" - Lider: "+lider.time.nome+"</p>" +
// 																			 "<div id='placeholder_"+lider.id+"' class=''></div>");
										 				// Show the initial default chart
										 				
										 				//loadChartPie( $('#placeholder_pie #placeholder_'+lider.id) , dataGrafico);
										 				$('#id_lider').html("<b class='text-info'>Lider:</b> " + lider.time.nome + "<span class='text-muted'> ("+lider.grupo.descricao+")</span>");
										 				$('#id_lider').html( $('#id_lider').html() + "<p class='text-muted'>" + 
										 																lider.pontos+" pontos | " + 
										 																(lider.golsPro - lider.golsContra) + " Saldo de gols | " + 
										 																roundToTwo(lider.pontos * 100 / ((lider.jogos == 0 ? 1 : lider.jogos) * 3))+" %</p>");
														loadChartPie(placeholder, dataGrafico);
													});
									});							   
								});
				}); 				
 				
			});

			function roundToTwo(num) {    
			    return +(Math.round(num + "e+2")  + "e-2");
			}
			
			//function labelFormatter(label, series) {
			//	return "<div style='font-size:8pt; text-align:center; padding:2px; color:white;'>" + label + "<br/>" + Math.round(series.percent) + "%</div>";
			//}			

	</script>


