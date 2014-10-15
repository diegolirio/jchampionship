<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

             <!-- Page Heading -->
             <h1 class="page-header">Campeonato</h1>               
             
             <div class="row">
                  <div class="col-lg-12">
                      <ol class="breadcrumb">
                          <li>
                              <i class="fa fa-dashboard"></i>  <a href="${pageContext.request.contextPath}">Campeonato</a>
                          </li>
                          <li class="active">
                              <i class="fa fa-bar-chart-o"></i> Edições
                          </li>
                      </ol>
                  </div> 
             </div>
             <!-- /.row -->
                

            <div class="row">
            	<c:forEach var="e" items="${edicoes}">
	                <div class="col-lg-6 col-md-6">
	                    <div class="panel panel-success">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-comments fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge"><a href="${pageContext.request.contextPath}/edicao/${e.id}">${e.campeonato.descricao} ${e.descricao}</a></div>
	                                    <div>${e.status.descricao}</div>
	                                </div>
	                            </div>
	                        </div>
	                        <a href="${pageContext.request.contextPath}/edicao/${e.id}">
	                            <div class="panel-footer">
	                                <span class="pull-left">Entrar</span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a>
	                    </div>
	                </div>
	            </c:forEach>
           </div>
               