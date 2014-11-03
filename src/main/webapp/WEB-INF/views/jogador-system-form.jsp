<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="form" action="${pageContext.request.contextPath}/jogador/post">
	<h1 class="text-info"> Jogador</h1>
	
	<input type="hidden" value="${not empty jogador ? jogador.id : 0}" name="id">
	
	<div class="form-group col-lg-4">
		<label>Nome </label> <span id="id_message_nome"></span>
		<input class="form-control" type="text" name="nome" value="${jogador.nome}" autofocus>
	</div>			
	
	<div class="form-group col-lg-4">
		<label>Posição </label> <span id="id_message_posicao"></span>
		<select class="form-control" name="posicao.id">
			<option value="">Selecione a Posição...</option>
			<c:forEach var="p" items="${posicoes}"> 
				<option value="${p.id}" ${jogador.posicao.id == p.id ? 'selected' : ''}>${p.descricao}</option>
			</c:forEach>
		</select>
	</div>
	
	<input type="hidden" name="uriFoto" value="${pageContext.request.contextPath}/static/quartashow/img/jogadores/jogador_no_photo.png"> 
 
	<br/>
		
	<a href="${pageContext.request.contextPath}/jogador/by/edicao/1" class="btn btn-default" onclick="window.close();">Cancelar</a>
	<input type="submit" class="btn btn-success" name="btnSave" value="Salvar">
	<c:if test="${param.page ne 'popup'}">
		<input type="submit" class="btn btn-success" name="btnSave" value="Salvar e cadastrar Novo">
	</c:if>
		
	<br/><br/>
</form>
 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common/ui-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common/common-valid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/jogador-controller.js"></script>
