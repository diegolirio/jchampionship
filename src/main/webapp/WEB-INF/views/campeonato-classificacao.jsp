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
	                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
	                    <div class="panel panel-success">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
										<!-- <i class="fa fa-comments fa-5x"></i> -->
										<img alt="${e.id}" src="${pageContext.request.contextPath}${e.campeonato.imgName}">
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge"><a href="${pageContext.request.contextPath}/edicao/${e.id}">${e.campeonato.descricao} <br/><span class="text-info">${e.descricao}</span></a></div>
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
	            
<!-- 				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 text-center"> -->
<!-- 					Facebook -->
<!-- 						<div id="fb-root"></div> -->
<!-- 						<script> -->
<!-- // 							(function(d, s, id) { -->
<!-- // 								var js, fjs = d.getElementsByTagName(s)[0]; -->
<!-- // 								if (d.getElementById(id)) return; -->
<!-- // 								js = d.createElement(s); js.id = id; -->
<!-- // 								js.src = "//connect.facebook.net/pt_BR/all.js#xfbml=1"; -->
<!-- // 								fjs.parentNode.insertBefore(js, fjs); -->
<!-- // 							}(document, 'script', 'facebook-jssdk')); -->
<!-- 						</script> -->
<!-- 					Facebook      		 -->
<!-- 					 Facebook        -->
<!-- 						  <div class="fb-like-box"  -->
<!-- 							 data-href="http://www.facebook.com/asiscoweb"  -->
<!-- 							 data-width="100%"  -->
<!-- 							 data-height="200"  -->
<!-- 							 data-show-faces="true"  -->
<!-- 							 data-stream="false"  -->
<!-- 							 data-header="true"> -->
<!-- 						</div>               -->
<!-- 					 Facebook  -->
<!-- 		         </div>  	             -->
	            
	            
		   		<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 text-center">
		
					<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
					<!-- QuartaShowPage Header 230X100 -->
					<ins class="adsbygoogle"
						 style="display:inline-block;width:320px;height:100px"
						 data-ad-client="ca-pub-1041989301102612"
						 data-ad-slot="4832115487"></ins>
					<script>
					(adsbygoogle = window.adsbygoogle || []).push({});
					</script>		
												
			  </div>	
			  
			  <br/><br/><br/><br/>             
           </div>
	           
               