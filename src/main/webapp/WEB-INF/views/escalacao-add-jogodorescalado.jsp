<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="form" action="">
	<h2 class="text-info"><small>Adicionar Jogador para Escalação - ${time.nome}</small> </h2>
	
	<input type="hidden" value="${time.id}" name="time.id">
	<input type="hidden" value="${escalacao.id}" name="escalacao.id">
	
	<div class="form-group col-lg-4">
		<label>Jogador</label> <span id="id_message_jogador"></span>
		<select class="form-control" name="jogador.id">
			<option value="">Selecione o Jogador...</option>
			<c:forEach var="j" items="${jogadores}">
				<option value="${j.id}">${j.nome}</option>
			</c:forEach>
		</select>
	</div>					
 
	<br/>
		
	<a href="#" class="btn btn-default" onclick="window.close();">Cancelar</a>
	
	<input type="submit" class="btn btn-success" value="Salvar" />
		
	<br/><br/>
</form>
 
<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common-valid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/escalacao-controller.js"></script>
