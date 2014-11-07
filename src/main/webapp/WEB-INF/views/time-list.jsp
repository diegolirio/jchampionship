<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<h1 class="page-header text-success">Times  <small>${edicao.campeonato.descricao} ${edicao.descricao}</small></h1>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Times </h3>
                </div>
                <div class="panel-body"> 
                	<c:if test="${not empty usuario && not empty admin && admin}">
                    	<a href="${pageContext.request.contextPath}/grupo/time/0?next=${requestScope['javax.servlet.forward.request_uri']}" class="btn btn-outline btn-success">Adicionar Novo Time ao Campeonato</a>
                    </c:if>
                    <br/>
                    <!--  Table -->
					<table class="table table-striped table-hover well">
						<thead>
							<tr class="text-danger">
								<td >#</td>
								<td >Time</td>
								<c:if test="${not empty usuario && not empty admin && admin}"><td></td></c:if>
							</tr>
						</thead>
						<tbody id="id_tbody">
							<c:forEach var="t" items="${times}" varStatus="status">
									<tr>
										<td><small>${t.id}</small></td>  
										<td><a href="${pageContext.request.contextPath}/time/${t.id}/edicao/${edicao.id}">${t.nome}</a></td>
										<c:if test="${not empty usuario && not empty admin && admin}">
											<td>
												<small><a href="${pageContext.request.contextPath}/time/system/${t.id}?next=${requestScope['javax.servlet.forward.request_uri']}">editar</a></small> 
												| <small><a href="${pageContext.request.contextPath}/time/system/delete/${t.id}" id="idDeleteJogador">excluir</a></small>
											</td>
										</c:if>
									</tr>
							</c:forEach>
						</tbody>								
					</table>    
					
                </div>
            </div>
        </div>
    </div>
    <!-- /.row -->
    
<%--     <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script> --%>
              
                
