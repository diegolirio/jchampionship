<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<form id="form" action="${pageContext.request.contextPath}/time/post?page=N" method="post">
		<h1 class="text-info"> ${time.nome}</h1>
		
		<input type="hidden" value="${time.id}" name="id">
		
		<div class="form-group col-lg-4">
			<label>Nome do Time <small id="id_view" class="text-warning">?</small></label> <span id="id_message_nome"></span>
			<input class="form-control" type="text" name="nome" value="${time.nome}">
		</div>						
	 
		<br/>
			
		<a href="${pageContext.request.contextPath}" class="btn btn-default" onclick="window.close();">Cancelar</a>
		
		<a href="#" class="btn btn-success saveTime" type="submit" >Salvar</a>
			
		<br/><br/>
		
	</form>

	<c:if test="${time.id > 0}">

	    <div class="row" id="id_tabelaJ">
	        <div class="col-lg-12">
	            <div class="panel panel-success"> 
	                <div class="panel-heading">
	                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Jogadores </h3>
	                </div>
	                <div class="panel-body">
		                               
		                <div class="row">
		                	<div class="col-lg-8">
								<select class="form-control" id="id_jogadores" name="jogador.id">
									<option value="">Selecione o Jogador...</option>
									<c:forEach var="j" items="${jogadoresAll}">
										<option value="${j.id}">${j.nome}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-2">
								<a href="javascript:undefined" class="btn btn-info addJogador">Adicionar Jogador</a>
							</div>
							<div class="col-lg-2">
								<a href="${pageContext.request.contextPath}/jogador/page/simple?page=popup" onclick="showWindowPopup(this.href, 500, 600); return false;">Novo Jogador</a>
							</div>							
						</div>		
						<br/><br/> 
		                               
	                    <!--  Table -->                                
						<table class="table table-striped table-hover well">
							<thead>
								<tr>
									<td></td>
									<td >Nome</td>
									<td title="Excluir">#</td>
								</tr>
							<tbody id="id_tbody">
								<c:forEach var="j" items="${time.jogadores}">
									<tr>
										<td>${j.posicao.descricao}</td>
										<td>${j.nome}</td>
										<td><a href="${pageContext.request.contextPath}/time/system/${time.id}/remove/jogador/${j.id}" class="removeJogadorTime"><span class="text-danger">excluir</span></a></td>
									</tr>									
								</c:forEach>
							</tbody>								
						</table>                                
		                               
		                               
	                </div>
	            </div>
	        </div>
	    </div>
    
    </c:if>


<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common-valid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/time-controller.js"></script>

