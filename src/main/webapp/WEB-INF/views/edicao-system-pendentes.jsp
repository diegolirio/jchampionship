<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

               <!-- Page Heading -->
               
	            <h1 class="page-header">
	                Edições  &nbsp; <a href="${pageContext.request.contextPath}/edicao/system/nova" class="btn btn-success">Criar Novo Campeonato</a>
	            </h1>               
               
                <div class="row">
                    <div class="col-lg-12">
						<ol class="breadcrumb">		
							<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}">Campeonato</a></li>
							<li class="text-warning" style="font-size:30px;"><i class="glyphicon glyphicon-star"></i> Edições</li>
							<li class="active"><i class="fa fa-edit"></i> Grupos</li>
							<li class="active"><i class="fa fa-edit"></i> Times</li>
							<li class="active"><i class="fa fa-edit"></i> Jogos</li>
							<li class="active"><i class="fa fa-edit"></i> Escalação</li>
							<li class="active"><i class="fa fa-edit"></i> Visão Geral</li>		
						</ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Edições Pendentes </h3>
                            </div>
                            <div class="panel-body">
                                
                                
                                <!--  Table -->
                                
								<table class="table table-striped table-hover well">
									<thead>
										<tr class="text-danger">
											<td title="ID">ID</td>
											<td >Descrição</td>
											<td title="Campeonato">Campeonato</td>
											<td title="Pendente">Status</td>
											<td title="Ações">#</td>
										</tr>
									<tbody id="id_tbody">
										<c:forEach var="e" items="${edicoes}">
											<tr>
												<td title="ID">${e.id}</td>
												<td >${e.descricao}</td>
												<td >${e.campeonato.descricao}</td>
												<td >${e.status.descricao}</td>
												<td >
													<a href="/jchampionship/edicao/system/${e.id}/grupos">concluir </a>
													<a href="/jchampionship/edicao/delete/${e.id}" id="idDeleteEdicao" class="text-danger"> excluir</a>
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
                
                <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script>
                
