<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="formDeleteClassificacao" action="${pageContext.request.contextPath}/classificacao/delete/${classificacao.id}">
	<h1 class="text-danger"> Deseja Excluir Time ?</h1>
	
	<input type="hidden" value="${classificacao.id}" name="id">
	
	<h3 class="text-info">${classificacao.time.nome}</h3>
	<h3><small>Grupo:</small> ${classificacao.grupo.descricao}</h3>
	
	<br/>
	<hr/>
	
	<a href="#" class="btn btn-default" onclick="window.close();">Cancelar</a>
	
	<a href="#" class="btn btn-danger" id="id_excluir_classificacao">Excluir</a>
		
	<br/><br/>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/classificacao-controller.js"></script>
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
