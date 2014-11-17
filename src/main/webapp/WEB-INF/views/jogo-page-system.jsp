<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<!-- Placar Responsive -->
		<h1 class="page-header col-lg-12 col-md-12 col-sm-12 visible-lg visible-md visible-sm"> 
			<span class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeA.id}/edicao/${jogo.grupo.edicao.id}">${jogo.timeA.nome}</a></span>
			<span class="text-center text-danger col-md-1" id="id_rA" >${jogo.resultadoA}</span>
			<span class="text-center text-muted col-md-1">X</span>
			<span class="text-center text-danger col-md-1" id="id_rB">${jogo.resultadoB}</span>
			<span class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeB.id}/edicao/${jogo.grupo.edicao.id}">${jogo.timeB.nome}</a></span>
		</h1>		
		<div class="page-header col-xs-12 visible-xs text-center">
			<table class="table">
				<tr>
					<td><h3 class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeA.id}/edicao/${jogo.grupo.edicao.id}">${jogo.timeA.nome}</a></h3></td>
					<td><h3 class="text-center text-danger col-md-1" id="id_rA" >${jogo.resultadoA}</h3></td>
				</tr>
				<tr>
					<!-- <h3 class="text-center text-muted col-md-1">X</h3> -->
					<td><h3 class="text-center col-md-4"><a href="${pageContext.request.contextPath}/time/${jogo.timeB.id}/edicao/${jogo.grupo.edicao.id}">${jogo.timeB.nome}</a></h3></td>
					<td><h3 class="text-center text-danger col-md-1" id="id_rB">${jogo.resultadoB}</h3></td>
				</tr>
			</table>		
		</div>
		<!-- Fim Placar Responsive -->
		 
		<div class="text-muted pull-right">
			<c:if test="${jogo.status.id != 1 && not empty usuario && not empty admin && admin}">
				<p><a href="${pageContext.request.contextPath}/jogo/${jogo.id}/retornStatus" id="idVoltarStatusJogo" class="btn btn-default btn-danger btn-outline"><span class="glyphicon glyphicon-arrow-left"> Voltar p/ ${jogo.status.id == 2 ? 'Pendente' : 'Em andamento'}</span></a></p> 
			</c:if>		
			<p class="text-primary"> 
				<img src="${pageContext.request.contextPath}/static/quartashow/img/${jogo.status.imgName}" /> ${jogo.status.descricao}
			</p>
			<p>Rodada: ${jogo.rodada}</p>		
			<p>Local: ${jogo.local.descricao} / Harbito: ${jogo.harbito.nome}</p>  
			<p>Data: <fmt:formatDate value="${jogo.dataHora}" pattern="dd/MM/yyyy"/> </p>
		</div>
		
		<c:if test="${jogo.status.id == 2 && not empty usuario && not empty admin && admin}">
			<a href="${pageContext.request.contextPath}/escalacao/system/${jogo.id}/add/evento/1" class="btn btn-outline btn-info" onclick="showWindowPopup(this.href, 600, 800); return false;" >Adicionar Gol</a>
			<a href="${pageContext.request.contextPath}/escalacao/system/${jogo.id}/add/evento/2" class="btn btn-outline btn-warning" onclick="showWindowPopup(this.href, 600, 800); return false;">Cartão Amarelo</a>
			<a href="${pageContext.request.contextPath}/escalacao/system/${jogo.id}/add/evento/3" class="btn btn-outline btn-danger" onclick="showWindowPopup(this.href, 600, 800); return false;">Cartão Vermelho</a>
		</c:if>

		<br/><br/><br/> 

	    <div class="row">
	        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> 
	        
				<c:if test="${jogo.status.id == 1 && not empty usuario && not empty admin && admin}">
					<a href="${pageContext.request.contextPath}/escalacao/post/jogo/${jogo.id}" class="btn btn-outline btn-success btn-lg btn-block" id="id_add_escalacao_jogo">Adicionar informações da Partida</a>
				</c:if>	    	        
	        
	        	<c:if test="${jogo.status.id != 1}">
	        	
		            <div class="panel panel-primary">
		                <div class="panel-heading">
		                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Escalação </h3>
		                </div>
		                <div class="panel-body">
									<p>
										<img src="${pageContext.request.contextPath}/static/quartashow/img/gol.png" height="12"/> <small class="text-muted">Gol</small> &bull;
										<img src="${pageContext.request.contextPath}/static/quartashow/img/cartao-amarelo.png" height="12"/> <small class="text-muted">C. Amarelo</small> &bull;
										<img src="${pageContext.request.contextPath}/static/quartashow/img/cartao-vermelho.png" height="12"/> <small class="text-muted">C. Vermelho</small>
<%-- 										<img src="${pageContext.request.contextPath}/static/quartashow/img/cartao-vermelho.png" height="12"/> <small class="text-muted">Falta</small> --%>
									</p>
		                      
		                    	<div class="table-responsive col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<!--  Table -->
									<table class="table">
										<thead class="text-warning">
											<tr >
												<th class="text-center" colspan="3">${escalacao.jogo.timeA.nome}</th>
												<th></th>
											</tr> 
										</thead>								
										<tbody id="id_tbody">
											<c:forEach var="je" items="${escalacao.jogadoresEscalados}">
												<c:if test="${escalacao.jogo.timeA.id == je.time.id}">
													<tr class="${jogo.resultadoA > jogo.resultadoB ? 'success' : 'danger'}" >
														<td>
															<c:if test="${jogo.status.id == 2 && not empty usuario && not empty admin && admin}">
																<a href="${pageContext.request.contextPath}/escalacao/jogadorEscalado/delete/${je.id}" onclick="showWindowPopup(this.href, 400, 600); return false;" > <span class="text-danger">excluir</span></a>
															</c:if>
														</td>
														<td><img alt="foto" src="${je.jogador.uriFoto}" title="${je.jogador.nome}" class="img-responsive img-circle" height="30" width="30"/></td>
														<td class="jogador${je.id}"> 
															 <img src="${pageContext.request.contextPath}/static/quartashow/img/${je.jogador.posicao.imgName }" alt="${je.jogador.posicao.descricao}"/>
															 <a href="${pageContext.request.contextPath}/jogador/${je.jogador.id}/edicao/${jogo.grupo.edicao.id}">${je.jogador.nome}</a>
														</td>
														<td> 
															<c:forEach var="ce" items="${je.eventos}">
																<img src="${pageContext.request.contextPath}/static/quartashow/img/${ce.evento.imgName}" alt="${ce.evento.descricao}" />
															</c:forEach> 
															<c:if test="${fn:length(je.eventos) > 0 && jogo.status.id == 2 && not empty usuario && not empty admin && admin}">
																<a href="${pageContext.request.contextPath}/escalacao/jogadorEscalado/${je.id}/eventos/delete" onclick="showWindowPopup(this.href, 400, 600); return false;" > 
																	<img src="${pageContext.request.contextPath}/static/quartashow/img/lixeira.png" alt="Excluir" title="Excluir Gol, Cartão amarelo ou Cartão vermelho" class="pull-right" />
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
	
		                    	<div class="table-responsive col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<!--  Table -->
									<table class="table">
										<thead class="text-warning">
											<tr>
												<th class="text-center" colspan="3">${escalacao.jogo.timeB.nome}</th>
												<th></th>
											</tr> 
										</thead>								
										<tbody id="id_tbody">     
											<c:forEach var="je" items="${escalacao.jogadoresEscalados}">
												<c:if test="${escalacao.jogo.timeB.id == je.time.id}">
													<tr class="${jogo.resultadoA < jogo.resultadoB ? 'success' : 'danger'}" >
													    <td>
													    	<c:if test="${jogo.status.id == 2 && not empty usuario && not empty admin && admin}">
													    		<a href="${pageContext.request.contextPath}/escalacao/jogadorEscalado/delete/${je.id}" onclick="showWindowPopup(this.href, 400, 600); return false;" > <span class="text-danger">excluir</span></a>
													    	</c:if>
													    </td>
													    <td><img alt="foto" src="${je.jogador.uriFoto}" title="${je.jogador.nome}" class="img-responsive img-circle" height="30" width="30"/></td>
														<td class="jogador${je.id}"> 
															 <img src="${pageContext.request.contextPath}/static/quartashow/img/${je.jogador.posicao.imgName }" alt="${je.jogador.posicao.descricao}"/>
															 <a href="${pageContext.request.contextPath}/jogador/${je.jogador.id}/edicao/${jogo.grupo.edicao.id}">${je.jogador.nome}</a>
														</td>
														<td> 
															<c:forEach var="ce" items="${je.eventos}">
																<img src="${pageContext.request.contextPath}/static/quartashow/img/${ce.evento.imgName}" alt="${ce.evento.descricao}" />
															</c:forEach>														
															<c:if test="${fn:length(je.eventos) > 0 && jogo.status.id == 2 && not empty usuario && not empty admin && admin}">
																<a href="${pageContext.request.contextPath}/escalacao/jogadorEscalado/${je.id}/eventos/delete" onclick="showWindowPopup(this.href, 400, 600); return false;" > 
																	<img src="${pageContext.request.contextPath}/static/quartashow/img/lixeira.png" alt="Excluir" title="Excluir Gol, Cartão amarelo ou Cartão vermelho" class="pull-right" />
																</a>
															</c:if>															
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
		         </c:if>
	        </div>
	    </div>
	    <!-- /.row -->
	    
		<c:if test="${jogo.status.id == 2 && not empty usuario && not empty admin && admin}"> 
			<a href="${pageContext.request.contextPath}/jogo/finalizar/${jogo.id}" class="btn btn-outline btn-success btn-lg btn-block" id="id_finalizar_jogo">Finalizar Jogo</a>
		</c:if>		    
    
    	<br/><br/>	
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common/ui-common.js"></script>
    <script src="${pageContext.request.contextPath}/static/quartashow/js/jogo-controller.js"></script>
              
                
