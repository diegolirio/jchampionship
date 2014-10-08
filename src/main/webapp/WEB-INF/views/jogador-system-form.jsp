<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="form" action="${pageContext.request.contextPath}/jogador/post">
	<h1 class="text-info"> Jogador</h1>
	
	<input type="hidden" value="${jogador.id}" name="id">
	
	<div class="form-group col-lg-4">
		<label>Nome </label> <span id="id_message_nome"></span>
		<input class="form-control" type="text" name="nome">
	</div>						
 
	<br/>
		
	<a href="#" class="btn btn-default" onclick="window.close();">Cancelar</a>
	
	<a href="#" class="btn btn-success saveJogador">Salvar</a>
		
	<br/><br/>
</form>
 
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common-valid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/jogador-controller.js"></script>
