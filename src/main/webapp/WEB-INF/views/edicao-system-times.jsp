<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1 class="page-header">Adicionar Times</h1>

<!-- Page Heading -->
<div class="row">

	<div class="col-lg-12">

		<ol class="breadcrumb">
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}">Campeonato</a></li>
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}/edicao/nova/${edicao.id}">Edição</a></li>
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/grupos">Grupos</a></li>
			<li class="active"><i class="glyphicon glyphicon-star text-warning"></i> Times</li>
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
					<i class="fa fa-edit"></i> Times - ${edicao.campeonato.descricao} - ${edicao.descricao}  
				</h3>
			</div>
			<div class="panel-body">

				<div class="col-lg-12">

					<form id="id_form" method="POST" action="${pageContext.request.contextPath}/classificacao/post">

						<div class="form-group col-lg-4">
							<label>Time </label> <span id="id_message_time"></span>
							<a href="${pageContext.request.contextPath}/time/page/simple?idSelected=id_times" onclick="showWindowPopup(this.href, 750, 900); return false;"><i class="glyphicon glyphicon-plus"></i></a> 
							<select class="form-control" id="id_times" name="time.id">
								<option value="">Selecione o Time...</option>
								<c:forEach var="t" items="${times}">
									<option value="${t.id}">${t.nome}</option>
								</c:forEach>
							</select>
						</div>
						
						<div class="form-group col-lg-4">
							<label>Grupo </label> <span id="id_message_grupo"></span>
							<select class="form-control" id="id_grupo" name="grupo.id">
								<option value="">Selecione o Grupo...</option>
								<c:forEach var="g" items="${grupos}">
									<option value="${g.id}">${g.descricao}</option>
								</c:forEach>
							</select>
						</div>
						
						<br/>
						
						<input type="submit" class="btn btn-success addClassificacao" value="Adicionar Time ao Grupo" >

					</form>
										
				</div>


				<c:forEach var="g" items="${grupos}">
					<div class="row"> 
						<div class="col-lg-12">
							<h3 class="text-danger">Grupo: ${g.descricao}</h3>
							<table class="table table-striped table-hover well">
								<thead class="text-danger">
									<tr>
										<td title="ID">ID</td>
										<td >Col</td>
										<td >Time</td>
										<td >J</td>
										<td >V</td>
										<td >E</td>
										<td >D</td>
										<td >Pontos</td>
										<td title="Excluir">Excluir</td>
									<tr>
								</thead>
								<tbody id="id_tbody_${g.id}">
									<c:forEach var="c" items="${g.classificacoes}">
										<tr>
											<td>${c.id}</td>
											<td title="Colocacao" class="text-info">${c.colocacao}º</td>
											<td >${c.time.nome}</td>
											<td >${c.jogos}</td>
											<td >${c.vitorias}</td>
											<td >${c.empates}</td>
											<td >${c.derrotas}</td>
											<td >${c.pontos}</td>											
											<td><a href="${pageContext.request.contextPath}/time/delete_confirm/${c.id}" onclick="showWindowPopup(this.href); return false;">Excluir</a></td>
										</tr>
									</c:forEach>
								</tbody>								
							</table>       				
						</div>
					</div>
				</c:forEach> 
				<hr/>
				 
				<a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/jogos" class="btn btn-success pull-right" id="id_prox_class">
					Cadastrar Jogos <i class="glyphicon glyphicon-share-alt"></i>
				</a>

			</div>
		</div>
		
	</div>
</div>
<!-- /.row -->


<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/classificacao-controller.js"></script>
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common-valid.js"></script>
 