<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1 class="page-header text-success">${edicao.campeonato.descricao} ${edicao.descricao}</h1>

	<c:forEach var="g" items="${edicao.grupos}">
	 
	    <div class="row">
	        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	            <div class="panel panel-primary">
	                <div class="panel-heading">
	                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Classificação ${g.descricao} </h3>
	                </div>
	                <div class="panel-body">
	                    
	                    <div class="table-responsive"> 
		                    <!--  Table -->
							<table class="table table-striped table-hover well">
								<thead>
									<tr class="text-danger">
										<td class="text-center">Col</td>
										<td >Time</td>
										<td class="text-center">Pontos</td>
										<td class="text-center">J</td>
										<td class="text-center">V</td>
										<td class="text-center">E</td>
										<td class="text-center">D</td>
										<td class="text-center">GP</td>
										<td class="text-center">GC</td>
										<td class="text-center">SG</td>
										<td class="text-center" title="percentual de aproveitamento">%</td>
									</tr>
								</thead>
								<tbody id="id_tbody">
									<c:forEach var="c" items="${g.classificacoes}">
										<tr class="">
											<td title="Colocacao" class="text-info text-center"><b>${c.colocacao}º</b></td>
											<td ><a href="${pageContext.request.contextPath}/time/${c.time.id}">${c.time.nome}</a></td>
											<td class="text-center"><b>${c.pontos}</b></td>
											<td class="text-center">${c.jogos}</td>
											<td class="text-center">${c.vitorias}</td>
											<td class="text-center">${c.empates}</td>
											<td class="text-center">${c.derrotas}</td>
											<td class="text-center">${c.golsPro}</td>
											<td class="text-center">${c.golsContra}</td>
											<td class="text-center">${c.golsPro - c.golsContra}</td>										
											<td class="text-center"><small>${c.pontos * 100 / ((c.jogos == 0 ? 1 : c.jogos) * 3)}</small></td>
										</tr>									
									</c:forEach>
								</tbody>								
							</table>    
						</div>						
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- /.row -->
	    
	    <!-- publicidade -->
	    <div class="row">
	    	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<!-- Azul_Suave_728X90 -->
				<ins class="adsbygoogle"
				     style="display:inline-block;width:100%;height:90px"
				     data-ad-client="ca-pub-1041989301102612"
				     data-ad-slot="2407772281"></ins>
				<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
				</script>	 
			</div>   		
	    </div>
	     
	     <br/>
	     
	    <div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            Jogos 
                        </div>
                        <div class="panel-body">
                        	<c:forEach var="j" items="${g.jogos}">
						        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">   
						        	<h3 class="text-info">
						        		<div class="table-responsive">
						        			<a href="${pageContext.request.contextPath}/jogo/${j.id}">
								        		<table class="table well">
								        			<thead>
								        				<tr>
								        					<td colspan="5"><small>Rodada: ${j.rodada}</small></td>
								        				</tr>
								        			</thead>
								        			<tbody>
								        				<tr>
								        					<td>${j.timeA.nome}</td>
								        					<td>
								        						<c:if test="${j.status.id != 1}">
								        							<span class="text-danger">${j.resultadoA}</span>
								        						</c:if>
															</td>	
															<td><span class="text-muted">X</span></td>
															<td>				
											        			<c:if test="${j.status.id != 1}"> 
											        				<span class="text-danger">${j.resultadoB}</span>
											        			</c:if>			
											        		</td>
											        		<td>${j.timeB.nome}</td>											        					
								        				</tr>
								        			</tbody>
								        			<tfoot>
								        				<tr>
								        					<td colspan="2">
								        						<small><span class=""><img src="${pageContext.request.contextPath}/static/quartashow/img/${j.status.imgName}"/> (${j.status.descricao})</span></small>
															</td>
															<td colspan="2">
																<small>${j.local.descricao}</small>
															</td>
															<td>
								        						<small><fmt:formatDate value="${j.dataHora}" pattern="dd/MM/yyyy"/></small> 
								        					</td>
								        				</tr>
								        		</table>
								        	</a>
								        </div>   
						        		
						        		<br/>
						        	</h3>
						        </div>                        	
                        	</c:forEach>
                        	<c:if test="${not empty usuario && not empty admin && admin}">
						        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">   
						        	<br/>
						        	<a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/jogos" class="btn btn-block btn-outline btn-success" >
						        		Cadastrar Novo jogo 
						        	</a> 
						        </div> 
						    </c:if>                          	
                        </div>
                        <div class="panel-footer">
                            ...
                        </div>
                    </div> 
                    <!-- /.col-lg-4 -->
                </div>	   
         </div> 
	    
	</c:forEach>
	    
    <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script>
              
                
