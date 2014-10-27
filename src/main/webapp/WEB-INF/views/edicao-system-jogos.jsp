<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1 class="page-header">Jogos</h1>

<!-- Page Heading -->
<div class="row">

	<div class="col-lg-12">

		<c:if test="${edicao.status.id == 1}"> <!-- Pendente -->
			<ol class="breadcrumb">		
				<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}">Campeonato</a></li>
				<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}/edicao/nova/${edicao.id}">Edição</a></li>
				<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/grupos">Grupos</a></li>
				<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/times">Times</a> </li>
				<li class="active"><i class="glyphicon glyphicon-star text-warning"></i> Jogos</li>
				<li class="active"><i class="fa fa-edit"></i> Escalação</li>
				<li class="active"><i class="fa fa-edit"></i> Visão Geral</li>		
			</ol>
		</c:if>
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

					<jsp:include page="jogo-form.jsp"></jsp:include>  
  
 				</div>
				<hr/>
				 
				<c:if test="${edicao.status.id == 1}"> <!-- Pendente --> 
					<a href="${pageContext.request.contextPath}/edicao/system/${edicao.id}/confirmacao" class="btn btn-info pull-right" id="id_prox_jogos">
						Próximo <i class="glyphicon glyphicon-share-alt"></i>
					</a>
				</c:if>

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
 