<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="formDelete" action="${pageContext.request.contextPath}/jogo/delete/${jogo.id}">
	<h1 class="text-danger"> Deseja Excluir Jogo ?</h1>
	
	<input type="hidden" value="${jogo.id}" name="id">
	
	<h3>${jogo.timeA.nome} <span class="text-muted">X</span> ${jogo.timeB.nome}</h3>
	
	<a href="#" class="btn btn-default" onclick="window.close();">Cancelar</a>
	
	<input type="submit" value="Excluir" class="btn btn-danger">
		
	<br/><br/>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/jogo-controller.js"></script>
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
