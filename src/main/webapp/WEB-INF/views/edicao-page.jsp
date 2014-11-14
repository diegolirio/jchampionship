<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	</h1>
	
	<h3 class="page-header text-success visible-xs">		
		${edicao.campeonato.descricao} ${edicao.descricao}		     	
	</h3>	

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
	</c:forEach>
	
	<br/><br/> 
	
	<jsp:include page="jogo-list.jsp"></jsp:include>
    <c:if test="${not empty usuario && not empty admin && admin}">
        <div class="row">
	        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">   
	        	<br/>
	        	<a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/jogos" class="btn btn-block btn-outline btn-success" >
	        		Cadastrar Novo jogo 
	        	</a> 
	        </div> 
	    </div>
    </c:if>  	
	
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
	    
    <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script>
              
                
