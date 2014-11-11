<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- http://www.flotcharts.org/flot/examples/interacting/index.html -->

	<h3 class="page-header text-success">Estatisticas  <small>${edicao.campeonato.descricao} ${edicao.descricao}</small></h3>

    <div class="row">
        <div class="col-lg-12">
          
          	<c:forEach var="g" items="${edicao.grupos}">
          		<p>${g.descricao}</p>
          		<hr/>
          		<c:forEach var="ch" items="${g.classificacoes}">
          			<p>Rodada: ${ch.rodada}</p>
          			<p><b>${ch.time.nome} - ${ch.colocacao}�</b></p> 
          		</c:forEach>
          	</c:forEach>
          
        </div>
    </div>
    <!-- /.row -->
    
<%--     <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script> --%>
              
                
