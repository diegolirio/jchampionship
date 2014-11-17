<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

   <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand text-success" href="${pageContext.request.contextPath}" >Quarta<span class="text-warning">Show</span>.com</a>
        </div>
        <!-- /.navbar-header -->


        <ul class="nav navbar-top-links navbar-right">
        	<li><a class="btn btn-social-icon btn-facebook" href="https://www.facebook.com/quartashowpontocom" target="_blank"><i class="fa fa-facebook"></i>acebook</a></li>
        	<!-- 
        
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-messages"> 
                    <li>
                        <a href="#">
                            <div>
                                <strong>Diego Lirio</strong>
                                <span class="pull-right text-muted">
                                    <em>Yesterday</em>
                                </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                <span class="pull-right text-muted">
                                    <em>Yesterday</em>
                                </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                <span class="pull-right text-muted">
                                    <em>Yesterday</em>
                                </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>Read All Messages</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-messages --
            </li>
            -->
            <!-- /.dropdown --
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-tasks">
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 1</strong>
                                    <span class="pull-right text-muted">40% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                        <span class="sr-only">40% Complete (success)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 2</strong>
                                    <span class="pull-right text-muted">20% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                        <span class="sr-only">20% Complete</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 3</strong>
                                    <span class="pull-right text-muted">60% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                        <span class="sr-only">60% Complete (warning)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 4</strong>
                                    <span class="pull-right text-muted">80% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                        <span class="sr-only">80% Complete (danger)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Tasks</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-tasks --
            </li>
            -->
            <!-- /.dropdown --
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-alerts">
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-comment fa-fw"></i> New Comment
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                <span class="pull-right text-muted small">12 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-envelope fa-fw"></i> Message Sent
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-tasks fa-fw"></i> New Task
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-alerts --
            </li>
            -->
            <c:if test="${empty usuario}">
            	<li><a href="${pageContext.request.contextPath}/usuario/login?next=${requestScope['javax.servlet.forward.request_uri']}"><i class="fa fa-sign-out fa-fw"></i> Login</a></li>
            </c:if>
            <!-- /.dropdown -->
            <c:if test="${not empty usuario}">
	            <li class="dropdown">
	                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	                    <i class="fa fa-user fa-fw"></i> ${usuario.nome } <i class="fa fa-caret-down"></i>
	                </a>
	                <ul class="dropdown-menu dropdown-user">
	                    	<li><a href="${pageContext.request.contextPath}/usuario/system/perfil"><i class="fa fa-user fa-fw"></i> Perfil</a></li>
							<!-- <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li> -->
	                    	<li class="divider"></li>
	                    	<li><a href="${pageContext.request.contextPath}/usuario/logout"><i class="fa fa-sign-out fa-fw"></i> Sair</a></li>
	                </ul>
	                <!-- /.dropdown-user -->
	            </li>
	        </c:if>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
<!--                     <li class="sidebar-search"> -->
<!--                         <div class="input-group custom-search-form"> -->
<!--                             <input type="text" class="form-control" placeholder="Search..."> -->
<!--                             <span class="input-group-btn"> -->
<!--                             	<button class="btn btn-default" type="button"> -->
<!--                                 	<i class="fa fa-search"></i> -->
<!--                             	</button> -->
<!--                         	</span> -->
<!--                         </div> -->
                        <!-- /input-group -->
<!--                     </li> -->
                    <li>
                        <a href="${pageContext.request.contextPath}"><i class="fa fa-dashboard fa-fw"></i> Campeonato</a>
                    </li>
                    
                    <c:if test="${edicao != null}">
	                    <li>
	                        <a href="${pageContext.request.contextPath}/edicao/${edicao.id}"><i class="fa fa-dashboard fa-fw"></i> Classificação</a>
	                    </li>
	                    <li>
	                        <a href="${pageContext.request.contextPath}/jogadorInfoEdicao/artilharia/by/edicao/${edicao.id}"><i class="fa fa-dashboard fa-fw"></i> Artilharia</a>
	                    </li>	                    
	                    <li>
	                        <a href="${pageContext.request.contextPath}/time/by/edicao/${edicao.id}"><i class="fa fa-dashboard fa-fw"></i> Times</a>
	                    </li>
	                    <li>
	                        <a href="${pageContext.request.contextPath}/jogador/by/edicao/${edicao.id}"><i class="fa fa-dashboard fa-fw"></i> Jogadores</a>
	                    </li>
	                    <li>
	                        <a href="${pageContext.request.contextPath}/edicao/${edicao.id}/estatisticas"><i class="fa fa-dashboard fa-fw"></i> Estatísticas</a>
	                    </li>	                    
                    </c:if>                    

					<c:if test="${usuario.superUsuario == true}">
	                    <li>
	                        <a href="${pageContext.request.contextPath}/usuario/system/perfil/0"><i class="fa fa-fw fa-file"></i> Criar novo Usuário</a>
	                    </li>         
                    </c:if>
                    
                    <c:if test="${not empty usuario && not empty admin && admin}">
	                    <li>
	                        <a href="${pageContext.request.contextPath}/edicao/system"><i class="fa fa-fw fa-file"></i> Criar novo Campeonato</a>
	                    </li>         
                    </c:if>   
					<div class="text-center hidden-xs">
						<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
						<!-- 180X150 -->
						<ins class="adsbygoogle"
						style="display:inline-block;width:180px;height:150px"
						data-ad-client="ca-pub-1041989301102612"
						data-ad-slot="9501256682"></ins>
						<script>
						(adsbygoogle = window.adsbygoogle || []).push({});
						</script>
					</div>                     
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>