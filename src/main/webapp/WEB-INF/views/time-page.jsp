<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		
				
	<h1 class="page-header">
		${time.nome} 
		<c:if test="${not empty usuario && not empty admin && admin}">
    		<a href="${pageContext.request.contextPath}/time/system/${time.id}">
   				<span class="glyphicon glyphicon-pencil text-muted"></span>
   			</a>
   		</c:if>
	</h1>
	
	
    <!-- Timeline CSS -->
    <link href="${pageContext.request.contextPath}/static/sb2/css/plugins/timeline.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath}/static/sb2/css/plugins/morris.css" rel="stylesheet"> 
    
    
    <!-- Flot Charts JavaScript -->
    <script src="${pageContext.request.contextPath}/static/sb2/js/plugins/flot/jquery.flot.js"></script>
    <script src="${pageContext.request.contextPath}/static/sb2/js/plugins/flot/jquery.flot.pie.js"></script>
    <script src="${pageContext.request.contextPath}/static/sb2/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
	<script type="text/javascript">
	
		$(function() {    
			
			var data = [],
			series = Math.floor(Math.random() * 6) + 3;
		
			for (var i = 0; i < series; i++) {
				data[i] = {
					label: "Series" + (i + 1),
					data: Math.floor(Math.random() * 100) + 1
				}
			}	
			
			var placeholder = $("#placeholder");

			$("#example-2").click(function() {

				placeholder.unbind();

				$("#title").text("Default without legend");
				$("#description").text("The default pie chart when the legend is disabled. Since the labels would normally be outside the container, the chart is resized to fit.");

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

				setCode([
					"$.plot('#placeholder', data, {",
					"    series: {",
					"        pie: {",
					"            show: true",
					"        }",
					"    },",
					"    legend: {",
					"        show: false",
					"    }",
					"});"
				]);
			});			
			
		});
	
	</script>    	
	 
	<c:if test="${not empty classificacao}">
		<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            ${classificacao.grupo.edicao.campeonato.descricao} ${classificacao.grupo.edicao.descricao }
                        </div>
                        <!-- /.panel-heading --> 
                        <div class="panel-body">
							<div class="col-lg-5">
								<div class="text-center">
									<ul class="text-muted">
										<li style="font-size: 20px;" class="text-primary">${classificacao.colocacao}º Colocado</li>
										<li style="font-size: 20px;" class="text-primary">${classificacao.pontos} pontos</li>
										<br/>
										<li>${classificacao.golsPro} Gols Pró</li>
										<li>${classificacao.golsContra} Gols Contra</li>
										<li>${classificacao.golsPro - classificacao.golsContra} Saldo de Gols</li>
										<li><fmt:formatNumber pattern="##0.00">${classificacao.pontos * 100 / ((classificacao.jogos == 0 ? 1 : classificacao.jogos) * 3)}</fmt:formatNumber>  % de aproveitamento</li>							
									</ul>
								</div>
							</div>                           
                        	<div class="col-lg-7">
								<jsp:include page="grafico_pizza_classificacao.jsp"></jsp:include>
							</div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>			
		</div>
	</c:if>
	
	<div class="col-lg-10">
		<div class="panel panel-primary">
			<div class="panel-heading">
				Jogadores
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead class="text-danger">
							<tr>
								<th class="text-muted">#</th>
								<th></th>
								<th>Nome</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="j" items="${time.jogadores}">
								<tr>
									<td class="text-muted"><img alt="foto" src="${j.uriFoto}" class="img-responsive img-circle" height="30" width="30"/> </td>
									<td><img src="${pageContext.request.contextPath}/static/quartashow/img/${j.posicao.imgName }" alt="${j.posicao.descricao}"/></td>
									<td>
										<h5><a href="${pageContext.request.contextPath}/jogador/${j.id}/edicao/${not empty edicao ? edicao.id : 0}"> ${j.nome} </a></h5>
									</td>
									<td></td> 
								</tr>
							</c:forEach>
							<c:if test="${fn:length(time.jogadores) <= 0 && not empty usuario && not empty admin && admin}">
								<h3>Não há jogadores para este time 
								<c:if test="${not empty usuario && not empty admin && admin}">
									<a href="${pageContext.request.contextPath}/time/system/${time.id}">cadastre agora</a>
								</c:if>
							</h3>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel-footer">
				Sujeito a Alterações...
			</div>
		</div>
	</div>
	
	<div class="row">
		<c:if test="${not empty edicao}">
			<jsp:include page="jogo-list.jsp"></jsp:include>
		</c:if>	
	</div>					
	
	<!-- End Page Time -->
    
    <script src="${pageContext.request.contextPath}/static/quartashow/js/time-controller.js"></script>
              
                
