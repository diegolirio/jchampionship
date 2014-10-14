<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1 class="page-header text-success">${edicao.campeonato.descricao} ${edicao.descricao}</h1>

	<c:forEach var="g" items="${edicao.grupos}">
	 
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-info">
	                <div class="panel-heading">
	                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Grupo ${g.descricao} </h3>
	                </div>
	                <div class="panel-body">
	                    
	                    <!--  Table -->
						<table class="table table-striped table-hover well">
							<thead>
								<tr class="text-danger">
									<td >Col</td>
									<td >Time</td>
									<td >J</td>
									<td >V</td>
									<td >E</td>
									<td >D</td>
									<td >Pontos</td>
								</tr>
							<tbody id="id_tbody">
								<c:forEach var="c" items="${g.classificacoes}">
									<tr class="">
										<td title="Colocacao" class="text-info">${c.colocacao}º</td>
										<td >${c.time.nome}</td>
										<td >${c.jogos}</td>
										<td >${c.vitorias}</td>
										<td >${c.empates}</td>
										<td >${c.derrotas}</td>
										<td >${c.pontos}</td>
									</tr>									
								</c:forEach>
							</tbody>								
						</table>    
						
						<h4 class="text-danger">Jogos</h4>
						
						<div class="row">
							
							<c:forEach var="j" items="${g.jogos}">
						        <div class="col-lg-6">   
						        	<h3 class="text-info">
						        		${j.timeA.nome} <small>X</small> ${j.timeB.nome} 
						        		<small> ${j.local.descricao} * 11/11/2011 </small> 
						        		
						        	</h3>
						        </div>
					        </c:forEach>
					    </div>
	                    
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- /.row -->
    </c:forEach>

	<a href="#${pageContext.request.contextPath}/edicao/system/${edicao.id}/set/status/2" class="btn btn-info pull-right" id="id_concluir_edicao">
		Concluir Cadastro <i class="glyphicon glyphicon-share-alt"></i>
	</a>
    
    <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script>
              
                
