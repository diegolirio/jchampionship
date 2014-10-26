<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		
				
	<h1 class="page-header">
		${time.nome} 
		<c:if test="${not empty usuario && not empty admin && admin}">
    		<a href="${pageContext.request.contextPath}/time/system/${time.id}">
   				<span class="glyphicon glyphicon-pencil text-muted"></span>
   			</a>
   		</c:if>
	</h1>
	
	<div class="col-lg-10">
		<div class="panel panel-primary">
			<div class="panel-heading">
				Jogadores
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
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
									<td class="text-muted" title="${j.id}"></td>
									<td><img src="${pageContext.request.contextPath}/static/quartashow/img/${j.posicao.imgName }" alt="${j.posicao.descricao}"/></td>
									<td>${j.nome}</td>
									<td></td>
								</tr>
							</c:forEach>
							<c:if test="${fn:length(time.jogadores) <= 0 && true}">
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
				Panel Footer
			</div>
		</div>
	</div>					
	
	<!-- End Page Time -->
    
    <script src="${pageContext.request.contextPath}/static/quartashow/js/time-controller.js"></script>
              
                
