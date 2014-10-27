<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="formDeleteEventoJE" action="">
	<h3 class="text-danger"> Excluir Eventos do Jogador...</h3>
	
	<input type="hidden" value="${jogadorEscalado.id}" name="id">
	
	<h2>${jogadorEscalado.jogador.nome} <small> ( Time: ${jogadorEscalado.time.nome} )</small> </h2>
	
	<div class="form-group col-lg-4">
		<label>Selecione o Evento que deseja excluir </label> <span id="id_message_evento"></span>
		<select class="form-control" name="collectionEventoId">
			<option value=""></option>
			<c:forEach var="ce" items="${jogadorEscalado.eventos}">
				<option value="${ce.id}">${ce.evento.descricao}</option>
			</c:forEach>
		</select>
	</div>		
	
	<a href="#" class="btn btn-default" onclick="window.close();">Cancelar</a>
	
	<input type="submit" value="Retirar" class="btn btn-danger">
		
	<br/><br/>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/escalacao-controller.js"></script>
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
