<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1 class="text-danger"> Deseja Excluir Grupo ?</h1>

<h1>${grupo.descricao}</h1>

<a href="#" class="btn btn-default" onclick="window.close();">Cancelar</a>

<a href="${pageContext.request.contextPath}/grupo/delete/${grupo.id}" class="btn btn-danger" id="id_excluir_grupo">Excluir</a>

<br/><br/>