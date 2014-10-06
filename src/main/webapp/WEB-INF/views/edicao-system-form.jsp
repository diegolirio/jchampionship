<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<h1 class="page-header">Novo Campeonato</h1>


<!-- Page Heading -->
<div class="row">

	<div class="col-lg-12">

		<ol class="breadcrumb">
			<li><i class="glyphicon glyphicon-ok text-success"></i> <a href="${pageContext.request.contextPath}">Campeonato</a></li>
			<li class="active"><i class="glyphicon glyphicon-star text-warning"></i> Edição</li>
			<li class="active"><i class="fa fa-edit"></i> Grupos</li>
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
					<i class="fa fa-edit"></i> Edição
				</h3>
			</div>
			<div class="panel-body">


				<div class="col-lg-6">

					<form id="id_form_edicao" method="POST">

						<input type="hidden" name="id" value="${edicao.id}" >

						<div class="form-group">
							<label>Descrição da Edição do Campeonato <small	class="text-muted">Ex: 2014</small></label> <span id="id_message_descricao"></span> 
							<input class="form-control"	name="descricao">
						</div>

						<div class="form-group">
							<label>Campeonato</label> <span id="id_message_campeonatoId"></span>
							<select class="form-control" id="id_campeonatos" name="campeonato.id">
								<option value="">----</option>
							</select>
						</div>

						<input type="submit" class="btn btn-success btnFormEdicaoSave" value="Salvar" >

					</form>
				</div>


			</div>
		</div>
		
	</div>
</div>
<!-- /.row -->

<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/campeonato-controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script>

