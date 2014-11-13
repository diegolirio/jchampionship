<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- http://www.flotcharts.org/flot/examples/interacting/index.html -->

    <div class="row">
        <div class="col-lg-12">
           <h4 class="page-header text-success">Estatisticas  <small>${edicao.campeonato.descricao} ${edicao.descricao}</small></h4>
           <jsp:include page="classificacaoHist-grafico-linha.jsp"></jsp:include>
        </div>
    </div>
    <!-- /.row -->
    <div class="row">
    	<!-- Lider de cada Grupo | grafico de pizza v,d, e + -->
    	<jsp:include page="classificacao-list-lider-grafico-pizza.jsp"></jsp:include>
    </div>
    <br/><br/><hr/>
    <div class="row">
    	<!-- Artilheiros(lista) | media de gols por partida -->
    </div>
    <div class="row">
    	<!-- Times(lista) menos vazados | media de gols contra por partida -->
    </div>
    <div class="row">
    	<%-- TODO: Analisar TimeInfoEdicao para registrar qtde de faltas, cartoes,  --%>
    	<!-- Times(lista) mais levou cartão vermelho | qtde -->
    </div> 
    <div class="row">
    	<!-- Times(lista) mais levou cartão amarelo | qtde -->
    </div>    
<!--     <div class="row"> -->
<!--     	Time mais fez faltas | qtde -->
<!--     </div> -->
    <div class="row">
    	<!-- Quem mais levou cartão | qtde -->
    </div>    
    
<%--     <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script> --%>
              
                
