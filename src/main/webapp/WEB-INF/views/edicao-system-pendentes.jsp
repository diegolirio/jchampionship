<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

               <!-- Page Heading -->
               
	            <h1 class="page-header">
	                Edições  &nbsp; <a href="${pageContext.request.contextPath}/edicao/system/nova" class="btn btn-success">Criar Novo Campeonato</a>
	            </h1>               
               
                <div class="row">
                    <div class="col-lg-12">
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="${pageContext.request.contextPath}">Campeonato</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-bar-chart-o"></i> Edições
                            </li>
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
										<tr>
											<td title="ID">ID</td>
											<td >Descrição</td>
											<td title="Campeonato">Campeonato</td>
											<td title="Pendente">Pendente</td>
											<td title="Concluir">#</td>
										</tr>
									<tbody id="id_tbody">
										<!--<center><img src="/media/images/load_32.gif"/></center>-->
									</tbody>								
								</table>                                
                                
                                
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
                
                <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script>
                <script type="text/javascript">
                		getEdicoesPendentes();
                </script>
                
