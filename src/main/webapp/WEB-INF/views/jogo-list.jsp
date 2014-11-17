<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:forEach var="g" items="${edicao.grupos}">
	<h5 class="text-muted">${g.descricao}</h5> 
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
</c:forEach>	