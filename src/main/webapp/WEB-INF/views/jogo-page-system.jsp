<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<h1 class="page-header col-md-12"> 
			<span class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeA.id}">${jogo.timeA.nome}</a></span>
			<span class="text-center text-danger col-md-1" id="id_rA" >${jogo.resultadoA}</span>
			<span class="text-center text-muted col-md-1">X</span>
			<span class="text-center text-danger col-md-1" id="id_rB">${jogo.resultadoB}</span>
			<span class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeB.id}">${jogo.timeB.nome}</a></span>
		</h1>
		
		<div class="text-muted pull-right"> 
			<p>Local: ${jogo.local.descricao} / Harbito: ${jogo.harbito.nome}</p>  
			<p class="text-primary"> <img src="${pageContext.request.contextPath}/static/quartashow/img/${jogo.status.imgName}" /> ${jogo.status.descricao}</p>
		</div>
		
		<c:if test="${jogo.status.id == 2 && not empty usuario && not empty admin && admin}">
			<a href="${pageContext.request.contextPath}/escalacao/system/${jogo.id}/add/evento/1" class="btn btn-outline btn-info" onclick="showWindowPopup(this.href, 600, 800); return false;" >Adicionar Gol</a>
			<a href="${pageContext.request.contextPath}/escalacao/system/${jogo.id}/add/evento/2" class="btn btn-outline btn-warning" onclick="showWindowPopup(this.href, 600, 800); return false;">Cart�o Amarelo</a>
			<a href="${pageContext.request.contextPath}/escalacao/system/${jogo.id}/add/evento/3" class="btn btn-outline btn-danger" onclick="showWindowPopup(this.href, 600, 800); return false;">Cart�o Vermelho</a>
		</c:if>

		<br/><br/>

	    <div class="row">
	        <div class="col-lg-12"> 
	            <div class="panel panel-primary">
	                <div class="panel-heading">
	                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Escala��o </h3>
	                </div>
	                <div class="panel-body">
	                    
							<c:if test="${jogo.status.id == 1 && not empty usuario && not empty admin && admin}">
								<a href="${pageContext.request.contextPath}/escalacao/post/jogo/${jogo.id}" class="btn btn-outline btn-success btn-lg btn-block" id="id_add_escalacao_jogo">Adicionar informa��es da Partida</a>
							</c:if>	                    
	                    
	                    	<div class="col-md-6">
								<!--  Table -->
								<table class="table">
									<thead class="text-warning">
										<tr>
											<td class="text-center" colspan="2">${escalacao.jogo.timeA.nome}</td>
											<td></td>
										</tr> 
									</thead>								
									<tbody id="id_tbody">
										<c:forEach var="je" items="${escalacao.jogadoresEscalados}">
											<c:if test="${escalacao.jogo.timeA.id == je.time.id}">
												<tr>
													<td><a href="${pageContext.request.contextPath}/escalacao/jogadorEscalado/delete/${je.id}" onclick="showWindowPopup(this.href, 400, 600); return false;" > <span class="text-danger">excluir</span></a></td>
													<td class="jogador${je.id}"> 
														 <img src="${pageContext.request.contextPath}/static/quartashow/img/${je.jogador.posicao.imgName }" alt="${je.jogador.posicao.descricao}"/>
														 ${je.jogador.nome}
													</td>
													<td> 
														<c:forEach var="ce" items="${je.eventos}">
															<img src="${pageContext.request.contextPath}/static/quartashow/img/${ce.evento.imgName}" alt="${ce.evento.descricao}" />
														</c:forEach> 
														<c:if test="${fn:length(je.eventos) > 0 && jogo.status.id == 2 && not empty usuario && not empty admin && admin}">
															<a href="${pageContext.request.contextPath}/escalacao/jogadorEscalado/${je.id}/eventos/delete" onclick="showWindowPopup(this.href, 400, 600); return false;" > 
																<img src="${pageContext.request.contextPath}/static/quartashow/img/lixeira.png" alt="Excluir" title="Excluir Gol, Cart�o amarelo ou Cart�o vermelho" class="pull-right" />
															</a>
														</c:if>
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>								
								</table>	        
								<c:if test="${jogo.status.id == 2 && not empty usuario && not empty admin && admin}"> 
									<a href="${pageContext.request.contextPath}/escalacao/${escalacao.id}/add/jogador/time/${jogo.timeA.id}" class="btn btn-outline btn-warning btn-xs btn-block" id="id_add_jogador_escalado_timeA" onclick="showWindowPopup(this.href, 400, 600); return false;">adicionar jogador para ${jogo.timeA.nome}</a>
								</c:if>            	
	                    	</div>

	                    	<div class="col-md-6">
								<!--  Table -->
								<table class="table">
									<thead class="text-warning">
										<tr>
											<td class="text-center" colspan="2">${escalacao.jogo.timeB.nome}</td>
											<td></td>
										</tr> 
									</thead>								
									<tbody id="id_tbody">
										<c:forEach var="je" items="${escalacao.jogadoresEscalados}">
											<c:if test="${escalacao.jogo.timeB.id == je.time.id}">
												<tr>
												    <td><a href="${pageContext.request.contextPath}/escalacao/jogadorEscalado/delete/${je.id}" onclick="showWindowPopup(this.href, 400, 600); return false;" > <span class="text-danger">excluir</span></a></td>
													<td class="jogador${je.id}"> 
														 <img src="${pageContext.request.contextPath}/static/quartashow/img/${je.jogador.posicao.imgName }" alt="${je.jogador.posicao.descricao}"/>
														 ${je.jogador.nome}
													</td>
													<td> 
														<c:forEach var="ce" items="${je.eventos}">
															<img src="${pageContext.request.contextPath}/static/quartashow/img/${ce.evento.imgName}" alt="${ce.evento.descricao}" />
															<c:if test="${jogo.status.id == 2 && not empty usuario && not empty admin && admin}">
																<a href="${pageContext.request.contextPath}/escalacao/jogadorEscalado/${je.id}/eventos/delete" onclick="showWindowPopup(this.href, 400, 600); return false;" >
																	<img src="${pageContext.request.contextPath}/static/quartashow/img/lixeira.png" alt="Excluir" title="Excluir Gol, Cart�o amarelo ou Cart�o vermelho" class="pull-right"/>
																</a>
															</c:if>
														</c:forEach>
													</td>													
												</tr>
											</c:if>
										</c:forEach>
									</tbody>								
								</table>
								<c:if test="${jogo.status.id == 2 && not empty usuario && not empty admin && admin}"> 
									<a href="${pageContext.request.contextPath}/escalacao/${escalacao.id}/add/jogador/time/${jogo.timeB.id}" class="btn btn-outline btn-warning btn-xs btn-block" id="id_add_jogador_escalado_timeB" onclick="showWindowPopup(this.href, 400, 600); return false;">adicionar jogador para ${jogo.timeB.nome}</a>
								</c:if>									                    	
	                    	</div>
	                    	
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- /.row -->
	    
		<c:if test="${jogo.status.id == 2 && not empty usuario && not empty admin && admin}"> 
			<a href="${pageContext.request.contextPath}/jogo/finalizar/${jogo.id}" class="btn btn-outline btn-success btn-lg btn-block" id="id_finalizar_jogo">Finalizar Jogo</a>
		</c:if>		    
    
    <script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
    <script src="${pageContext.request.contextPath}/static/quartashow/js/jogo-controller.js"></script>
              
                
