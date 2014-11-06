<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<div class="table-responsive visible-lg visible-md visible-sm">
		<table class="table">
			<tbody>
				<tr>
					<td><img src="${jogador.uriFoto}" class="img-circle" height="100"></td>
					<td><h1 class="page-header text-primary">${jogador.nome} <small>...</small></h1></td>
					<td><h1><small class="text-success">R$ 0,00</small></h1></td>
					<td></td>
				</tr> 
			</tbody>
		</table>
	</div>
	
	<div class="visible-xs text-center">
		<p><img src="${jogador.uriFoto}" class="img-circle" height="100"></p>
		<h2 class="page-header text-primary">${jogador.nome} </h2>
		<p><small>...</small></p>
		<h3><small class="text-success">R$ 0,00</small></h3>
		<br/><br/><br/>  
	</div>

    <c:if test="${not empty jogadorInfoEdicao}">
	    <div class="row">
	    	<table class="table text-center">
	    		<thead class="text-success">
	    			<tr>
	    				<td colspan="4"><b>${jogadorInfoEdicao.edicao.campeonato.descricao} ${jogadorInfoEdicao.edicao.descricao}</b></td>
	    			</tr>
	    		</thead>	    	
	    		<thead class="text-danger">
	    			<tr>
	    				<td>jogos</td>
	    				<td>C. Amarelo</td>
	    				<td>C. Vermelho</td>
	    				<td>Gols</td>
	    			</tr>
	    		</thead>	    	
	    		<tbody>
	    			<tr>
	    				<td>${jogadorInfoEdicao.jogos}</td>
	    				<td>${jogadorInfoEdicao.cartaoAmarelo}</td>
	    				<td>${jogadorInfoEdicao.cartaoVermelho}</td>
	    				<td>${jogadorInfoEdicao.gols}</td>
	    			</tr>
	    		</tbody>
	    	</table>
	    </div>
    </c:if>
    <br/><br/><br/> <br/><br/><br/> 
    
<%--     <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script> --%>
              
                
