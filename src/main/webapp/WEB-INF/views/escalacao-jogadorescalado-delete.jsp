<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="formDeleteJE" action="">
	<h3 class="text-danger"> Deseja Retirar Jogador Escalado ?</h3>
	
	<input type="hidden" value="${jogadorEscalado.id}" name="id">
	
	<h2>${jogadorEscalado.jogador.nome} <small> ( Time: ${jogadorEscalado.time.nome} )</small> </h2>
	
	
	<a href="#" class="btn btn-default" onclick="window.close();">Cancelar</a>
	
	<input type="submit" value="Retirar" class="btn btn-danger">
		
	<br/><br/>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/escalacao-controller.js"></script>
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
