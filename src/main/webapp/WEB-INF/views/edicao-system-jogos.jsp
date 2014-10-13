<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1 class="page-header">Jogos</h1>

<!-- Page Heading -->
<div class="row">

	<div class="col-lg-12">

		<ol class="breadcrumb">
		
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}">Campeonato</a></li>
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}/edicao/nova/${edicao.id}">Edição</a></li>
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/grupos">Grupos</a></li>
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/times">Times</a> </li>
			<li class="active"><i class="glyphicon glyphicon-star text-warning"></i> Jogos</li>
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
					<i class="fa fa-edit"></i> Jogos - ${edicao.campeonato.descricao} - ${edicao.descricao}  
				</h3>
			</div>
			<div class="panel-body">


				<div class="col-lg-12">

					<form id="id_form_jogo" method="POST" action="${pageContext.request.contextPath}/jogo/post">

<%-- 						<input type="hidden" name="id" value="${jogo.id}" > --%>

						<div class="form-group col-lg-4">
							<label>Grupo </label> <span id="id_message_grupo"></span> 
							<select class="form-control" id="id_grupos" name="grupo.id">
								<option value="">Selecione o Grupo...</option>
								<c:forEach var="g" items="${grupos}">
									<option value="${g.id}">${g.descricao}</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group col-lg-4">
							<label>Harbito </label> <span id="id_message_harbito"></span>
							<a href="${pageContext.request.contextPath}/harbito/page/simple" onclick="showWindowPopup(this.href, 400, 600); return false;"><i class="glyphicon glyphicon-plus"></i></a> 
							<select class="form-control" id="id_harbitos" name="harbito.id">
								<option value="" selected="selected">Selecione o Harbito...</option>
								<c:forEach var="h" items="${harbitos}">
									<option value="${h.id}">${h.nome}</option> 
								</c:forEach>
							</select>
						</div>						
						
						<div class="form-group col-lg-4">
							<label>Local </label> <span id="id_message_local"></span> 
							<a href="${pageContext.request.contextPath}/local/page/simple" onclick="showWindowPopup(this.href, 400, 600); return false;"><i class="glyphicon glyphicon-plus"></i></a>
							<select class="form-control" id="id_locais" name="local.id">
								<option value="" selected="selected">Selecione o Local...</option>
								<c:forEach var="l" items="${locais}">
									<option value="${l.id}">${l.descricao}</option>
								</c:forEach>
							</select>
						</div>						

						<div class="form-group col-lg-5">
							<label>Time A </label> <span id="id_message_timea"></span>
<%-- 							<a href="${pageContext.request.contextPath}/time/page/simple?idSelected=id_timea" onclick="showWindowPopup(this.href, 750, 900); return false;"><i class="glyphicon glyphicon-plus"></i></a>  --%>
							<select class="form-control" id="id_timea" name="timeA.id">
								<option value="" selected="selected">Selecione o Time A...</option>
								<c:forEach var="t" items="${times}">
									<option value="${t.id}">${t.nome}</option>
								</c:forEach>
							</select>
						</div>						
						<div class="form-group col-lg-2 text-center text-muted"><h1>X</h1></div>
						<div class="form-group col-lg-5">
							<label>Time B </label> <span id="id_message_timeb"></span> 
<%-- 							<a href="${pageContext.request.contextPath}/time/page/simple?idSelected=id_timeb" onclick="showWindowPopup(this.href, 750, 900); return false;"><i class="glyphicon glyphicon-plus"></i></a> --%>
							<select class="form-control" id="id_timeb" name="timeB.id">
								<option value="" selected="selected">Selecione o Time B...</option>
								<c:forEach var="t" items="${times}">
									<option value="${t.id}">${t.nome}</option>
								</c:forEach>
							</select>
						</div>																														
						
						<div class="row">
							<input type="submit" class="btn btn-success addJogo" value="Adicionar" >
						</div>
						
					</form>
										
				</div>
				
				<br/><br/>
				
				<div class="col-lg-12">
					<table class="table table-striped table-hover well">
						<thead>
							<tr class="text-danger">
								<td>ID</td>
								<td >Grupo</td>
								<td >Time A</td>
								<td ></td>
								<td class="text-center text-muted">X</td>
								<td ></td>
								<td >Time B</td>
								<td >Local</td>
								<td >Harbito</td>
								<td title="Excluir">Excluir</td>
							<tr>
						</thead>
						<tbody id="id_tbody">
							<c:forEach var="j" items="${jogos}">
								<tr>
									<td>${j.id}</td>
									<td >${j.grupo.descricao}</td>
									<td >${j.timeA.nome}</td>
									<td >${j.resultadoA}</td>
									<td class="text-center text-muted">X</td>
									<td >${j.resultadoB}</td>
									<td >${j.timeB.nome}</td>
									<td >${j.local.descricao}</td>
									<td >${j.harbito.nome}</td>
									<td><a href="${pageContext.request.contextPath}/jogo/delete_confirm/${j.id}" onclick="showWindowPopup(this.href); return false;">Excluir</a></td>
								</tr>
							</c:forEach>
						</tbody>								
					</table>       				
				</div>
 
				<hr/>
				 
				<a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/finalizar" class="btn btn-success pull-right" id="id_prox_jogos">
					Próximo <i class="glyphicon glyphicon-share-alt"></i>
				</a>

			</div>
		</div>
		
	</div>
</div>
<!-- /.row -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/campeonato-controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/grupo-controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/jogo-controller.js"></script>
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common-valid.js"></script>
 