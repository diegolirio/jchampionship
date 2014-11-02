<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<h1 class="page-header text-success">Jogadores  <small>${edicao.campeonato.descricao} ${edicao.descricao}</small></h1>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Jogadores </h3>
                </div>
                <div class="panel-body">
                    
                    <!--  Table -->
					<table class="table table-striped table-hover well">
						<thead>
							<tr class="text-danger">
								<td >#</td>
								<td >Jogador</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="id_tbody">
							<c:forEach var="j" items="${jogadores}" varStatus="status">
									<tr>
										<td><small>${j.id}</small></td>  
										<td>
											<a href="javascript:undefined" title="${j.nome}">
												<img src="${j.uriFoto}" class="img-circle" height="30" width="30"/> 
												${j.nome}
											</a>
										<td>${j.posicao.descricao}</td>
										</td>
										<td>
											<c:if test="${not empty usuario && not empty admin && admin}">
												<small><a href="${pageContext.request.contextPath}/jogador/system/form/${j.id}?next=${requestScope['javax.servlet.forward.request_uri']}">editar</a></small> | 
												<small><a href="${pageContext.request.contextPath}/jogador/system/delete/${j.id}" id="idDeleteJogador">excluir</a></small></td>
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
              
                
