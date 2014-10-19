<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<h1 class="page-header col-md-12"> 
			<span class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeA.id}">${jogo.timeA.nome}</a></span>
			<span class="text-center text-danger col-md-1">${jogo.resultadoA}</span>
			<span class="text-center text-muted col-md-1">X</span>
			<span class="text-center text-danger col-md-1">${jogo.resultadoB}</span>
			<span class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeB.id}">${jogo.timeB.nome}</a></span>
		</h1>
		
		<p class="text-muted pull-right"> 
			Local: ${jogo.local.descricao} / Harbito: ${jogo.harbito.nome}  
		</p>

		<c:if test="${escalacao != null && true}"> <!-- TODO: user admin -->
			<a href="${pageContext.request.contextPath}/jogo/system/${jogo.id}" class="text-success">Adicionar informa��es da Partida</a>
		</c:if>

	 
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-primary">
	                <div class="panel-heading">
	                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Escala��o </h3>
	                </div>
	                <div class="panel-body">
	                    
	                    <c:if test="${escalacao != null}">
	                    
	                    	<div class="col-md-6">
								<!--  Table -->
								<table class="table">
									<thead class="text-warning">
										<tr>
											<td class="text-center">${escalacao.jogo.timeA.nome}</td>
										</tr> 
									</thead>								
									<tbody id="id_tbody">
										<c:forEach var="je" items="${escalacao.jogadoresEscalados}">
											<c:if test="${escalacao.jogo.timeA.id == je.time.id}">
												<tr>
													<td class="text-center">${je.jogador.nome}</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>								
								</table>	                    	
	                    	</div>

	                    	<div class="col-md-6">
								<!--  Table -->
								<table class="table">
									<thead class="text-warning">
										<tr>
											<td class="text-center">${escalacao.jogo.timeB.nome}</td>
										</tr> 
									</thead>								
									<tbody id="id_tbody">
										<c:forEach var="je" items="${escalacao.jogadoresEscalados}">
											<c:if test="${escalacao.jogo.timeB.id == je.time.id}">
												<tr>
													<td class="text-center">${je.jogador.nome}</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>								
								</table>	                    	
	                    	</div>
						</c:if>
						<c:if test="${escalacao == null && true}"> <!-- TODO: user admin -->
							<a type="button" href="${pageContext.request.contextPath}/escalacao/post/jogo/${jogo.id}" class="btn btn-outline btn-success btn-lg btn-block" id="id_add_escalacao_jogo">Adicionar informa��es da Partida</a>
						</c:if>
						
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- /.row -->
    
    <script src="${pageContext.request.contextPath}/static/quartashow/js/jogo-controller.js"></script>
              
                
