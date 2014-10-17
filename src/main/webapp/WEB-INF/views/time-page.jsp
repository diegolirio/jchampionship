<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		
				
	<h1 class="page-header">
		${time.nome} 
		<c:if test="${true}"> <!-- TODO: if user admin -->
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
								<th>#</th>
								<th>Nome</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="j" items="${time.jogadores}">
								<tr>
									<td>${j.id}</td>
									<td>${j.nome}</td>
									<td></td>
									<td></td>
								</tr>
							</c:forEach>
							<c:if test="${fn:length(time.jogadores) <= 0 && true}"> <!-- TODO: if user admin -->
								<h3>Não há jogadores para este time, <a href="${pageContext.request.contextPath}/time/system/${time.id}">cadastre agora</a></h3>
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
              
                
