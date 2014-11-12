<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

   <div class="col-lg-12">
		<form id="id_form_jogo" method="POST" action="${pageContext.request.contextPath}/jogo/post">

			<input type="hidden" name="id" value="${jogo.id}" >
			<input type="hidden" name="status.id" value="${jogo.status.id}" >
			<input type="hidden" name="grupo.edicao.id" value="${jogo.grupo.edicao.id}" >

			<div class="form-group col-lg-1">
				<label><small>Rodada</small> </label> <span id="id_message_rodada"></span>
				<input class="form-control" type="text" name="rodada" title="somente numero" onclick="NumbersOnly(e); return false;" value="${jogo.rodada}">
			</div>

			<div class="form-group col-lg-3">
				<label>Grupo </label> <span id="id_message_grupo"></span> 
				<select class="form-control" id="id_grupos" name="grupo.id"  ${not empty jogo ? 'disabled="disabled"' : ''}>
					<option value="">Selecione o Grupo...</option> 
					<c:forEach var="g" items="${grupos}">
						<option value="${g.id}" ${jogo.grupo.id == g.id ? 'selected="selected"' : ''}>${g.descricao}</option>
					</c:forEach>
				</select> 
			</div>

			<div class="form-group col-lg-3">
				<label>Harbito </label> <span id="id_message_harbito"></span>
				<a href="${pageContext.request.contextPath}/harbito/page/simple" onclick="showWindowPopup(this.href, 400, 600); return false;"><i class="glyphicon glyphicon-plus"></i></a> 
				<select class="form-control" id="id_harbitos" name="harbito.id">
					<option value="" selected="selected">Selecione o Harbito...</option>
					<c:forEach var="h" items="${harbitos}">
						<option value="${h.id}" ${jogo.harbito.id == h.id ? 'selected="selected"' : ''}>${h.nome}</option> 
					</c:forEach>
				</select>
			</div>						
			
			<div class="form-group col-lg-3">
				<label>Local </label> <span id="id_message_local"></span> 
				<a href="${pageContext.request.contextPath}/local/page/simple" onclick="showWindowPopup(this.href, 400, 600); return false;"><i class="glyphicon glyphicon-plus"></i></a>
				<select class="form-control" id="id_locais" name="local.id">
					<option value="" selected="selected">Selecione o Local...</option>
					<c:forEach var="l" items="${locais}">
						<option value="${l.id}" ${jogo.local.id == l.id ? 'selected="selected"' : ''}>${l.descricao}</option>
					</c:forEach>
				</select>
			</div>						

			<div class="form-group col-lg-2">
				<label>Data e Hora </label> <span id="id_message_datahora"></span>
				<input class="form-control datepicker" type="text" name="dataHora" value="${jogo.dataHoraStrEN}">
			</div>

			<div class="form-group col-lg-5">
				<label>Time A </label> <span id="id_message_timea"></span>
<%-- 							<a href="${pageContext.request.contextPath}/time/page/simple?idSelected=id_timea" onclick="showWindowPopup(this.href, 750, 900); return false;"><i class="glyphicon glyphicon-plus"></i></a>  --%>
				<select class="form-control" id="id_timea" name="timeA.id" ${not empty jogo ? 'disabled="disabled"' : ''}>
					<option value="" selected="selected">Selecione o Time A...</option>
					<c:forEach var="t" items="${times}">
						<option value="${t.id}" ${jogo.timeA.id == t.id ? 'selected="selected"' : ''}>${t.nome}</option>
					</c:forEach>
				</select>
			</div>						
			<div class="form-group col-lg-2 text-center text-muted"><h1>X</h1></div>
			<div class="form-group col-lg-5">
				<label>Time B </label> <span id="id_message_timeb"></span> 
<%-- 							<a href="${pageContext.request.contextPath}/time/page/simple?idSelected=id_timeb" onclick="showWindowPopup(this.href, 750, 900); return false;"><i class="glyphicon glyphicon-plus"></i></a> --%>
				<select class="form-control" id="id_timeb" name="timeB.id" ${not empty jogo ? 'disabled="disabled"' : ''}>
					<option value="" selected="selected">Selecione o Time B...</option>
					<c:forEach var="t" items="${times}">
						<option value="${t.id}" ${jogo.timeB.id == t.id ? 'selected="selected"' : ''}>${t.nome}</option>
					</c:forEach>
				</select>
			</div>																														
			
			<br/>
			
			<a href="${pageContext.request.contextPath}/jogo/delete_confirm/${jogo.id}" onclick="showWindowPopup(this.href); return false;" class="btn btn-danger pull-right">Excluir</a>			
			<input type="submit" class="btn btn-success pull-right" value="${not empty jogo ? 'Salvar' : 'Adicionar Jogo'}" >
			
										
		</form>
							
	</div>
	
	
	<br/><br/>
	 
	<div class="col-lg-12">
		<table class="table table-striped table-hover well">
			<thead class="text-center">
				<tr class="text-danger">
					<!-- <td>ID</td> -->
					<td >Rodada</td>
					<td >Grupo</td>
					<td >Time A</td>
					<td ></td>
					<td class="text-center text-muted"></td>
					<td ></td>
					<td >Time B</td>
					<td >Local</td>
					<td >Harbito</td>
					<td>Data</td>
					<td title="Excluir">Excluir</td>
				<tr>
			</thead>
			<tbody id="id_tbody">
				<c:forEach var="j" items="${jogos}">
					<tr class="text-center">
						<%-- <td><small class="text-muted">${j.id}</small></td> --%>
						<td >${j.rodada}</td>
						<td >${j.grupo.descricao}</td>
						<td >${j.timeA.nome}</td>
						<td >${j.resultadoA}</td>
						<td class="text-muted">X</td>
						<td >${j.resultadoB}</td>
						<td >${j.timeB.nome}</td>
						<td >${j.local.descricao}</td>    
						<td >${j.harbito.nome}</td>
						<td ><fmt:formatDate value="${j.dataHora}" pattern="dd/MM/yyyy"/>   </td>
						<td>
							<c:if test="${j.status.id == 1}"> <!-- Pendente -->
								<a href="${pageContext.request.contextPath}/jogo/delete_confirm/${j.id}" onclick="showWindowPopup(this.href); return false;">Excluir</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>								
		</table>       				
	</div>
	
	<script src="${pageContext.request.contextPath}/static/quartashow/js/common/number-common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/jogo-controller.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common/ui-common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common/common-valid.js"></script>
	
	