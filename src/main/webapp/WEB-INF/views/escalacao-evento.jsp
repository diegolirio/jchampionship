<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<form id="formJE" action="${pageContext.request.contextPath}/escalacao/" method="post">

		<h1>Adicionar ${evento.descricao}</h1>
		
		<input type="hidden" value="${evento.id}" name="evento.id">
		
		<div class="form-group col-lg-4">
			<label>Jogador</label> <span id="id_message_jogador_escalado"></span>
			<select class="form-control" name="jogadorEscalado.id">
				<option value="">Selecione o Jogador...</option>
				<c:forEach var="je" items="${jogadoresEscalados}">
					<option value="${je.id}">${je.jogador.nome} - ${je.time.nome}</option>
				</c:forEach>
			</select>
		</div>						
	 
		<br/>
			
		<a href="${pageContext.request.contextPath}" class="btn btn-default" onclick="window.close();">Cancelar</a>
		
		<input class="btn btn-success saveEventoJogadorEscalado" type="submit" value="Salvar">
			
		<br/><br/>
		
	</form>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/escalacao-controller.js"></script>