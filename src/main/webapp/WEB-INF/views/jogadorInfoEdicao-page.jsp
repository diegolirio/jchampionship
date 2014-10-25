<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<h1 class="page-header text-success">Artilharia <small>${edicao.campeonato.descricao} ${edicao.descricao}</small></h1>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Artilharia </h3>
                </div>
                <div class="panel-body">
                    
                    <!--  Table -->
					<table class="table table-striped table-hover well">
						<thead>
							<tr class="text-danger">
								<td >Jogador</td>
								<td>J</td>
								<td>CA</td>
								<td>CV</td>
								<td>Gols</td>
							</tr>
						</thead>
						<tbody id="id_tbody">
							<c:forEach var="jie" items="${edicao.jogadoresInfoEdicao}">
								<tr>
									<td>${jie.jogador.nome}</td>
									<td><small>${jie.jogos}</small></td>
									<td><small>${jie.cartaoAmarelo}</small></td>
									<td><small>${jie.cartaoVermelho}</small></td>
									<td>${jie.gols}</td> 
								</tr>
							</c:forEach>
						</tbody>								
					</table>    
					
                </div>
            </div>
        </div>
    </div>
    <!-- /.row -->
    
<%--     <script src="${pageContext.request.contextPath}/static/quartashow/js/edicao-controller.js"></script> --%>
              
                
