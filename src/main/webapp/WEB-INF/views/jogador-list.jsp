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
                	<c:if test="${not empty usuario && not empty admin && admin}">
                    	<a href="${pageContext.request.contextPath}/jogador/system/form/0?next=${requestScope['javax.servlet.forward.request_uri']}" class="btn btn-outline btn-success">Novo Jogador</a>
                    	<br/><br/>
                    </c:if>
                             
                    <!--  Table -->
					<table class="table table-striped table-hover well">
						<thead>
							<tr class="text-danger">
								<th ></th>
								<th >Jogador</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="id_tbody">
							<c:forEach var="j" items="${jogadores}" varStatus="status">
									<tr>
										<td><small></small></td>  
										<td>
											<a href="${pageContext.request.contextPath}/jogador/${j.id}/edicao/${edicao.id}" title="${j.nome}">
												<img src="${j.uriFoto}" class="img-circle" height="30" width="30"/> 
												${j.nome}
											</a>
										</td>
										<td title="${j.posicao.descricao}"><img src="${pageContext.request.contextPath}/static/quartashow/img/${j.posicao.imgName }"/></td>
										<td>
											<c:if test="${not empty usuario && not empty admin && admin}">
												<small><a href="${pageContext.request.contextPath}/jogador/system/form/${j.id}?next=${requestScope['javax.servlet.forward.request_uri']}">editar</a></small> 
<%-- 												| <small><a href="${pageContext.request.contextPath}/jogador/system/delete/${j.id}" id="idDeleteJogador">excluir</a></small></td> --%>
											</c:if>
										</td>
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
              
                
