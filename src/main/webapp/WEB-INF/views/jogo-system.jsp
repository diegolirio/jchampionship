<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<h1 class="page-header col-md-12"> 
			<span class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeA.id}">${jogo.timeA.nome}</a></span>
			<span class="text-center text-danger col-md-1" id="id_rA" >${jogo.resultadoA}</span>
			<span class="text-center text-muted col-md-1">X</span>
			<span class="text-center text-danger col-md-1" id="id_rB">${jogo.resultadoB}</span>
			<span class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeB.id}">${jogo.timeB.nome}</a></span>
		</h1>
		
		<p class="text-muted pull-right"> 
			Local: ${jogo.local.descricao} / Harbito: ${jogo.harbito.nome}  
		</p>

		<a href="${pageContext.request.contextPath}/escalacao/system/${jogo.id}/add/evento/1" class="btn btn-outline btn-info" onclick="showWindowPopup(this.href, 600, 800); return false;" >Adicionar Gol</a>
		<a href="${pageContext.request.contextPath}/escalacao/system/${jogo.id}/add/evento/2" class="btn btn-outline btn-warning" onclick="showWindowPopup(this.href, 600, 800); return false;">Cartão Amarelo</a>
		<a href="${pageContext.request.contextPath}/escalacao/system/${jogo.id}/add/evento/3" class="btn btn-outline btn-danger" onclick="showWindowPopup(this.href, 600, 800); return false;">Cartão Vermelho</a>

		<br/><br/>

	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-primary">
	                <div class="panel-heading">
	                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Escalação </h3>
	                </div>
	                <div class="panel-body">
	                    
	                    	<div class="col-md-6">
								<!--  Table -->
								<table class="table">
									<thead class="text-warning">
										<tr>
											<td></td>
											<td class="text-center">${escalacao.jogo.timeA.nome}</td>
											<td></td>
										</tr> 
									</thead>								
									<tbody id="id_tbody">
										<c:forEach var="je" items="${escalacao.jogadoresEscalados}">
											<c:if test="${escalacao.jogo.timeA.id == je.time.id}">
												<tr>
													<td>${je.jogador.posicao.descricao}</td>
													<td class="text-center jogador${je.id}">${je.jogador.nome}</td>
													<td> 
														<c:forEach var="e" items="${je.eventos}">
															${e.descricao}
														</c:forEach> 
													</td>
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
											<td></td>
											<td class="text-center">${escalacao.jogo.timeB.nome}</td>
											<td></td>
										</tr> 
									</thead>								
									<tbody id="id_tbody">
										<c:forEach var="je" items="${escalacao.jogadoresEscalados}">
											<c:if test="${escalacao.jogo.timeB.id == je.time.id}">
												<tr>
													<td>${je.jogador.posicao.descricao}</td>
													<td class="text-center jogador${je.id}">${je.jogador.nome}</td>
													<td> 
														<c:forEach var="e" items="${je.eventos}">
															${e.descricao}
														</c:forEach> 
													</td>													
												</tr>
											</c:if>
										</c:forEach>
									</tbody>								
								</table>	                    	
	                    	</div>

	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- /.row -->
    
    <script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
    <script src="${pageContext.request.contextPath}/static/quartashow/js/jogo-controller.js"></script>
              
                
