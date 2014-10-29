<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<h3 class="page-header text-success">Perfil</h3>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> ${usuarioCadastro.nome} </h3>
                </div>
                <div class="panel-body">
	                    
					<form id="id_form_perfil" method="GET" action="" class="col-lg-6">

						<input type="hidden" name="id" value="${usuarioCadastro.id}" >
						
						<img src="${usuarioCadastro.uriImage}" class="img-circle" height="60" width="60"/>	
						<!-- <a href="#">alterar foto</a> -->

						<br/><br/> 

						<div class="form-group">
							<label>Nome </label> <span id="id_message_nome"></span> 
							<input class="form-control"	name="nome" value="${usuarioCadastro.nome }">
						</div>

						<div class="form-group">
							<label>E-mail</label> <span id="id_message_email"></span>
							<input class="form-control"	name="email" value="${usuarioCadastro.email }">
						</div>
						
						<c:if test="${usuarioCadastro.superUsuario}">
	              			<div class="form-group">
	                            <div class="checkbox">
	                                <label>
	                                    <input type="checkbox" value="${usuarioCadastro.superUsuario}" name="superUsuario">Super Usuário
	                                </label>
	                            </div>	
	                        </div>					
						</c:if>
						 
						<a href="#">alterar senha</a>
							
						<input type="submit" class="btn btn-success" value="Salvar" >

					</form>
						
	            </div>
	        </div>
	    </div>
	</div>
	<!-- /.row -->
	    
    <script src="${pageContext.request.contextPath}/static/quartashow/js/usuario-controller.js"></script>
              
                
