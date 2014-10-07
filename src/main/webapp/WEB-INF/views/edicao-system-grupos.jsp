<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1 class="page-header">Adicionar Grupos</h1>

<!-- Page Heading -->
<div class="row">

	<div class="col-lg-12">

		<ol class="breadcrumb">
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}">Campeonato</a></li>
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}/edicao/nova/${edicao.id}">Edição</a></li>
			<li class="active"><i class="glyphicon glyphicon-star text-warning"></i> Grupos</li>
			<li class="active"><i class="fa fa-edit"></i> Jogos</li>
			<li class="active"><i class="fa fa-edit"></i> Escalação</li>
			<li class="active"><i class="fa fa-edit"></i> Visão Geral</li>			
		</ol>
	</div>
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-edit"></i> Grupos - ${edicao.campeonato.descricao} - ${edicao.descricao}  
				</h3>
			</div>
			<div class="panel-body">


				<div class="col-lg-12">

					<form id="id_form_grupo" method="POST" action="${pageContext.request.contextPath}/grupo/post">

						<input type="hidden" name="id" value="${grupo.id}" >
						
						<input type="hidden" name="edicao.id" value="${edicao.id}" >

						<div class="form-group col-lg-6">
							<label>Descrição <small	class="text-muted">Ex: A, B ou Serie A</small></label> <span id="id_message_descricao"></span> 
							<input class="form-control"	name="descricao">
						</div>
						<br/>
						<input type="submit" class="btn btn-success addGrupo" value="Adicionar" >

					</form>
										
				</div>
				
				<div class="col-lg-12">
					<table class="table table-striped table-hover well">
						<thead>
							<tr>
								<td title="ID">ID</td>
								<td >Grupo</td>
								<td title="Excluir">Excluir</td>
							<tr>
						</thead>
						<tbody id="id_tbody">
							<c:forEach var="g" items="${grupos}">
								<tr>
									<td>${g.id}</td>
									<td>${g.descricao}</td>
									<td><a href="${pageContext.request.contextPath}/grupo/delete_confirm/${g.id}" onclick="showWindowPopup(this.href); return false;">Excluir</a></td>
								</tr>
							</c:forEach>
						</tbody>								
					</table>       				
				</div>
 
				<hr/>
				 
				<a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/jogos" class="btn btn-success pull-right" id="id_prox_gpos">
					Cadastrar Jogos <i class="glyphicon glyphicon-share-alt"></i>
				</a>

			</div>
		</div>
		
	</div>
</div>
<!-- /.row -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/campeonato-controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/grupo-controller.js"></script>
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/window-common.js"></script>
 