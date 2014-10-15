<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		

		<h1 class="page-header col-md-12"> 
			<span class="text-center col-md-3"><a href="${pageContext.request.contextPath}/time/${jogo.timeA.id}">${jogo.timeA.nome}</a></span>
			<span class="text-center text-danger col-md-1">${jogo.resultadoA}</span>
			<span class="text-center text-muted col-md-1">X</span>
			<span class="text-center text-danger col-md-1">${jogo.resultadoB}</span>
			<span class="text-center col-md-3"><a href="${pageContext.request.contextPath}/time/${jogo.timeB.id}">${jogo.timeB.nome}</a></span>
		</h1>
		
		<p class="text-muted pull-right"> 
			Local: ${jogo.local.descricao} / Harbito: ${jogo.harbito.nome}  
		</p>
	 
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-primary">
	                <div class="panel-heading">
	                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Escalação </h3>
	                </div>
	                <div class="panel-body">
	                    
	                    <!--  Table -->
						<table class="table table-striped table-hover well">
							<tbody id="id_tbody">
								<tr>
									<td class="text-center">${jogo.timeA.nome}</td>
									<td class="text-center">${jogo.resultadoA}</td>
									<td class="text-center">X</td>
									<td class="text-center">${jogo.resultadoB}</td>
									<td class="text-center">${jogo.timeB.nome}</td>
								</tr> 
							</tbody>								
						</table>    
						
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- /.row -->
    
    <script src="${pageContext.request.contextPath}/static/quartashow/js/jogo-controller.js"></script>
              
                
