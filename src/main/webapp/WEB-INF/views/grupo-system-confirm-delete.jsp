<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="formDeleteGrupo" action="${pageContext.request.contextPath}/grupo/delete/${grupo.id}">
	<h1 class="text-danger"> Deseja Excluir Grupo ?</h1>
	
	<input type="hidden" value="${grupo.id}" name="id">
	
	<h1>${grupo.descricao}</h1>
	
	<a href="#" class="btn btn-default" onclick="window.close();">Cancelar</a>
	
	<a href="#" class="btn btn-danger" id="id_excluir_grupo">Excluir</a>
		
	<br/><br/>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/grupo-controller.js"></script>
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
