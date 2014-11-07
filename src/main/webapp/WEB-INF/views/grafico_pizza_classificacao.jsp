<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<div class="demo-container">
		<div id="placeholder" class="demo-placeholder"></div>
	</div>
	     
	<link href="${pageContext.request.contextPath}/static/flot_charts/examples.css" rel="stylesheet" type="text/css">
	<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="../../excanvas.min.js"></script><![endif]-->
	<!-- 	<script language="javascript" type="text/javascript" src="jquery.js"></script> -->
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/flot_charts/jquery.flot.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/flot_charts/jquery.flot.pie.js"></script>
	
	<script type="text/javascript">
 
			$(function() {

				// Example Data
				//var data = [
				//	{ label: "Series1",  data: 10},
				//	{ label: "Series2",  data: 30},
				//	{ label: "Series3",  data: 90},
				//	{ label: "Series4",  data: 70},
				//	{ label: "Series5",  data: 80},
				//	{ label: "Series6",  data: 110}
				//];

				//var data = [
				//	{ label: "Series1",  data: [[1,10]]},
				//	{ label: "Series2",  data: [[1,30]]},
				//	{ label: "Series3",  data: [[1,90]]},
				//	{ label: "Series4",  data: [[1,70]]},
				//	{ label: "Series5",  data: [[1,80]]},
				//	{ label: "Series6",  data: [[1,0]]}
				//];

				//var data = [
				//	{ label: "Series A",  data: 0.2063},
				//	{ label: "Series B",  data: 38888}
				//];

				var data = [
					{ label: "Vitorias", data: ${classificacao.vitorias}, color: "#298A08"},
					{ label: "Empates",  data: ${classificacao.empates},  color: "#FACC2E"},
					{ label: "Derrotas", data: ${classificacao.derrotas}, color: "#B40404"}
				];				
				
				// Randomly Generated Data
				//var data = [],
				//	series = Math.floor(Math.random() * 6) + 3;

				//for (var i = 0; i < series; i++) {
				//	data[i] = {
				//		label: "Series" + (i + 1),
				//		data: Math.floor(Math.random() * 100) + 1
				//	}
				//}

				var placeholder = $("#placeholder");

				
				
				var loadChartPie = function() {

					placeholder.unbind();
					/*
					$.plot(placeholder, data, {
						series: {
							pie: { 
								show: true
							}
						},
						legend: {
							show: false
						}
					});
					*/
					
					$.plot(placeholder, data, {
						series: {
							pie: { 
								show: true,
								//radius: 1,
								tilt: 0.5,
								//label: {
									//show: true,
									//radius: 1,
									//formatter: labelFormatter,
									//background: { opacity: 0.8 }
								//},
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

				// Show the initial default chart
				loadChartPie();
			});
			
			function labelFormatter(label, series) {
				return "<div style='font-size:8pt; text-align:center; padding:2px; color:white;'>" + label + "<br/>" + Math.round(series.percent) + "%</div>";
			}			

	</script>


