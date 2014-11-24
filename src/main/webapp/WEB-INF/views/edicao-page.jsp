<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v2.0";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>

	<div class="fb-like pull-right" 
		 data-href="${requestScope['javax.servlet.forward.request_uri']}" 
		 data-width="20" data-height="20" 
		 data-layout="button_count" 
		 data-send="false">
    </div>	      
     
	<h1 class="page-header text-success visible-lg visible-md visible-sm">		
		${edicao.campeonato.descricao} ${edicao.descricao}	
		<small>
			<c:if test="${edicao.status.id == 2 && not empty usuario && not empty admin && admin}"> 
				<a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/set/status/${edicao.status.id-1}" id="idVoltarStatusEdicao" class="btn btn-default btn-danger btn-outline"><span class="glyphicon glyphicon-arrow-left"> Voltar p/ ${edicao.status.id == 2 ? 'Pendente' : 'Em andamento'}</span></a> 
			</c:if>				
		</small>	     	
	</h1>
	
	<h3 class="page-header text-success visible-xs">		
		${edicao.campeonato.descricao} ${edicao.descricao}		     	
	</h3>	
	
	<c:if test="${not empty podium}">
	   <div class="row text-center">
	   		<img src="${pageContext.request.contextPath}/${edicao.campeonato.imgName}"> 
			<c:if test="${podium.campeaoDefinido == true }">
				<h2 title="Campeão"><a href="${pageContext.request.contextPath}/time/${podium.campeao.id}/edicao/${edicao.id}"><span class="text-warning">${podium.campeao.nome}</span></a></h2>
			</c:if>
			<br/>
			<c:if test="${podium.viceCampeaoDefinido == true }">
				<h3 class="text-info"><small>Vice Campeão:</small> ${podium.viceCampeaoDefinido == true ? podium.viceCampeao.nome : ''}</h3>
			</c:if>
			<br/>
			<h4 class="text-danger"><small>Terceiro:</small> ${podium.terceiroColocadoDefinido == true ? podium.terceiroColocado.nome : ''}</h4>
	   </div>
	   <br/>
	   <hr/>  
	</c:if> 
	
	   <c:set var="active" value="${false}"/>

	   <div class="col-lg-12">

				 		 <!-- Nav tabs -->
		                 <ul class="nav nav-pills">
		                 	<c:forEach var="g" items="${edicao.grupos}" varStatus="status">  
		                     	<li class="${ (g.status.id == 2 || fn:length(edicao.grupos) == status.count) && active == false ? 'active' : ''}"><a href="#g${g.id}" data-toggle="tab">${g.descricao}</a></li>
	                     		<c:if test="${ g.status.id == 2 || fn:length(edicao.grupos) == status.count }">
		                     		<c:set var="active" value="${true}"></c:set>
		                     	</c:if>		
		                    </c:forEach>
		                 </ul>  
		                 <c:set var="active" value="${false}"/>
	 					 <!-- Tab panes -->
	 					 <br/>
		                 <div class="tab-content"> 
		                 	 <c:forEach var="g" items="${edicao.grupos}" varStatus="status">
		                 	 		                 	  
			                     <div class="tab-pane fade in ${ (g.status.id == 2 || fn:length(edicao.grupos) == status.count) && active == false ? 'active' : ''}" id="g${g.id}">
			                     	<c:if test="${ g.status.id == 2 || fn:length(edicao.grupos) == status.count }">
			                     		<c:set var="active" value="${true}"></c:set>
			                     	</c:if>		                 
	 
									<c:if test="${g.fase.id == 1}">
								 
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
																		<th class="text-center">Col</th>
																		<th >Time</th>
																		<th class="text-center">Pontos</th>
																		<th class="text-center">J</th>
																		<th class="text-center">V</th>
																		<th class="text-center">E</th>
																		<th class="text-center">D</th>
																		<th class="text-center">GP</th>
																		<th class="text-center">GC</th>
																		<th class="text-center">SG</th>
																		<th class="text-center" title="percentual de aproveitamento">%</th>
																	</tr>
																</thead>
																<tbody id="id_tbody">
																	<c:forEach var="c" items="${g.classificacoes}">
																		<tr class="">
																			<td title="Colocacao" class="text-info text-center"><b>${c.colocacao}º</b></td>
																			<td ><a href="${pageContext.request.contextPath}/time/${c.time.id}/edicao/${edicao.id}">${c.time.nome}</a></td>
																			<td class="text-center"><b>${c.pontos}</b></td>
																			<td class="text-center">${c.jogos}</td>
																			<td class="text-center">${c.vitorias}</td>
																			<td class="text-center">${c.empates}</td>
																			<td class="text-center">${c.derrotas}</td>
																			<td class="text-center">${c.golsPro}</td>
																			<td class="text-center">${c.golsContra}</td>
																			<td class="text-center">${c.golsPro - c.golsContra}</td>										
																			<td class="text-center"><small><fmt:formatNumber pattern="##0.00">${c.pontos * 100 / ((c.jogos == 0 ? 1 : c.jogos) * 3)}</fmt:formatNumber></small></td>
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
									    <br/>				
									    
									    <!-- publicidade -->
									    <div class="row">
									    	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center"> 
														<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
														<!-- QuartaShowPage Header 230X100 -->
														<ins class="adsbygoogle"
														     style="display:inline-block;width:320px;height:100px"
														     data-ad-client="ca-pub-1041989301102612"
														     data-ad-slot="4832115487"></ins>
														<script>
														(adsbygoogle = window.adsbygoogle || []).push({});
														</script>   
											</div>   	  		
									    </div>										    
										
										<br/>
										
										<%-- <jsp:include page="jogo-list.jsp"></jsp:include> --%>
										<c:forEach var="j" items="${g.jogos}">
										
										 	<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12" id="jogo${j.id}">   
											      	
											      		<div class="table-responsive">
											      			<a href="${pageContext.request.contextPath}/jogo/${j.id}">
											        		<table class="table well text-center">
											        			<thead>
											        				<tr>
											        					<td colspan="5" class="text-muted">
											        						<small> 
											        							Rodada: ${j.rodada} - <fmt:formatDate value="${j.dataHora}" pattern="dd/MM/yyyy"/>
											        						</small>
																			<c:if test="${not empty usuario && not empty admin && admin && j.status.id != 3}">
																	    		<a href="${pageContext.request.contextPath}/jogo/system/form/${j.id}">
																	   				<span class="glyphicon glyphicon-pencil text-muted pull-right pencil-edit"></span>
																	   			</a>    
																	   		</c:if>								        							
											        						
											        					</td>
											        				</tr>
											        			</thead>
											        			<tbody >
											        				<tr>
											        					<td>
											        						<h4 class="text-info">${j.timeA.nome}</h4>
											        					</td>
											        					<td>
											        						<c:if test="${j.status.id != 1}">
											        							<h4 class="text-danger">${j.resultadoA}</h4>
											        						</c:if>
																		</td>	
																		<td><h4 class="text-muted">X</h4></td>
																		<td>				
														        			<c:if test="${j.status.id != 1}"> 
														        				<h4 class="text-danger">${j.resultadoB}</h4>
														        			</c:if>			
														        		</td>
														        		<td>
														        			<h4 class="text-info">${j.timeB.nome}</h4>
														        		</td>											        					
											        				</tr>
											        			</tbody>
											        			<tfoot>
											        				<tr>
											        					<td colspan="3">
											        						<small><span class=""><img src="${pageContext.request.contextPath}/static/quartashow/img/${j.status.imgName}"/> (${j.status.descricao})</span></small>
																		</td>
																		<td colspan="2">
																			<small>${j.local.descricao}</small>
																		</td>
											        				</tr>
											        		</table>
											        	</a>
											        </div>   
											      	<br/>
								      			</div>
								      		</c:forEach>
										    <c:if test="${not empty usuario && not empty admin && admin && g.status.id == 2}">
												 <div class="row">
													<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
														<br/>
														<a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/jogos" class="btn btn-block btn-outline btn-success" >
															Cadastrar Novo jogo
														</a>
													</div>
													<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
														<br/>
														<a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/finalizarPrimeiraFase" class="btn btn-block btn-outline btn-danger" id="idFinalizarFase">
															Finalizar Fase
														</a>
													</div>
													<br/><br/>
												</div>
										    </c:if>  								      		
									 </c:if>	
									 <c:if test="${g.fase.sigla == '2'.charAt(0)}">
									 	<div class="row">
											<c:if test="${edicao.status.id == 2 && not empty usuario && not empty admin && admin}"> 
												<a href="${pageContext.request.contextPath}/edicao/${edicao.id}/Voltar/primeira/fase/" id="idVoltarEdicaoPrimeirafase" class="btn btn-danger btn-outline"><span class="glyphicon glyphicon-arrow-left"> Voltar p/ 1ª Fase</span></a> 
											</c:if>
										</div>									 
										<c:forEach var="j" items="${g.jogos}">											
										 	<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12" id="jogo${j.id}">   
											      	
											      		<div class="table-responsive">
											      			<a href="${pageContext.request.contextPath}/jogo/${j.id}">
											        		<table class="table well text-center">
											        			<thead>
											        				<tr>
											        					<td colspan="5" class="text-muted">
											        						<small>
																				<c:choose>
																				    <c:when test="${j.rodada == -1}">
																				       Final
																				    </c:when>
																				    <c:when test="${j.rodada == -3}">
																				        3º Lugar
																				    </c:when>
																				    <c:when test="${j.rodada == -2}">
																				        Semi-Final
																				    </c:when>
																				    <c:when test="${j.rodada == -4}">
																				        Quartas-de-Final
																				    </c:when>			
																				    <c:when test="${j.rodada == -8}">
																				        Oitavas-de-Final
																				    </c:when>																						    																			    																				    
																				    <c:otherwise>
																				        Mata-mata
																				    </c:otherwise>
																				</c:choose>
											        							- 
											        							<fmt:formatDate value="${j.dataHora}" pattern="dd/MM/yyyy"/>
											        						</small>
																			<c:if test="${not empty usuario && not empty admin && admin && j.status.id != 3}">
																	    		<a href="${pageContext.request.contextPath}/jogo/system/form/${j.id}">
																	   				<span class="glyphicon glyphicon-pencil text-muted pull-right pencil-edit"></span>
																	   			</a>    
																	   		</c:if>								        							
											        						
											        					</td>
											        				</tr>
											        			</thead>
											        			<tbody >
											        				<tr>
											        					<td>
											        						<h4 class="text-info">${j.timeA.nome}</h4>
											        					</td>
											        					<td>
											        						<c:if test="${j.status.id != 1}">
											        							<h4 class="text-danger">${j.resultadoA}</h4>
											        						</c:if>
																		</td>	
																		<td><h4 class="text-muted">X</h4></td>
																		<td>				
														        			<c:if test="${j.status.id != 1}"> 
														        				<h4 class="text-danger">${j.resultadoB}</h4>
														        			</c:if>			
														        		</td>
														        		<td>
														        			<h4 class="text-info">${j.timeB.nome}</h4>
														        		</td>											        					
											        				</tr>
											        			</tbody>
											        			<tfoot>
											        				<tr>
											        					<td colspan="3">
											        						<small><span class=""><img src="${pageContext.request.contextPath}/static/quartashow/img/${j.status.imgName}"/> (${j.status.descricao})</span></small>
																		</td>
																		<td colspan="2">
																			<small>${j.local.descricao}</small>
																		</td>
											        				</tr>
											        		</table>
											        	</a>
											        </div>   
											      	<br/>
								      			</div>
								      		</c:forEach>	
								      		<br/>
										    <!-- publicidade -->
										    <div class="row">
										    	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center"> 
															<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
															<!-- QuartaShowPage Header 230X100 -->
															<ins class="adsbygoogle"
															     style="display:inline-block;width:320px;height:100px"
															     data-ad-client="ca-pub-1041989301102612"
															     data-ad-slot="4832115487"></ins>
															<script>
															(adsbygoogle = window.adsbygoogle || []).push({});
															</script>   
												</div>   	  		
										    </div>		
										    <br/><br/>							      										 
									 </c:if>
				            	</div> <!-- /. tab-pane -->
		                	</c:forEach> <!-- /. forEach grupos -->
	                 	</div> <!-- /. tab-content  -->
	             
		 	</div>
	
	<br/><br/> 
    	
	<br/><br/> 
    
    <br/><br/>
	    
    <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script>
              
                
