<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Campeonato - QuartaShow.com</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/sb/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/sb/css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath}/static/sb/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/static/sb/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

		<jsp:include page="menu.jsp"></jsp:include>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header text-danger"> 
                            500 - Oops! Erro no Servidor!
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="${pageContext.request.contextPath}">Campeonato</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-bar-chart-o"></i> Classificação
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
 						<div class="col-lg-12">
                        	<div class="panel panel-primary">
                           		 <div class="panel-heading">
                               		 <h3 class="panel-title"><i class="fa fa-fw fa-table"></i>  500</h3>
                            	  </div>
                            	  <div class="panel-body">
                        
											<table class="table table-condensed table-hover table-striped table-bordered" >
												<thead>
													<tr class="danger text-red" valign="top">
														<td width="40%"><b>Error:</b></td>
														<td>${pageContext.exception}</td>
													</tr>
												</thead>
												<tbody>
													<tr class="success" valign="top">
														<td><b>URI:</b></td>
														<td>${pageContext.errorData.requestURI}</td>
													</tr>		
													<tr class="active" valign="top">
														<td><b>Status code:</b></td>
														<td>${pageContext.errorData.statusCode}</td>
													</tr>		
													<tr class="warning" valign="top">
														<td><b>Stack trace:</b></td>
														<td> 
															<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
																<p>${trace}</p>
															</c:forEach>
														</td>
													</tr>																											
												</tbody>
											</table>	                        
                        
                        
								  </div>
						    </div>
						</div>
                </div>
                <!-- /.row -->


            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery Version 1.11.0 -->
    <script src="${pageContext.request.contextPath}/static/sb/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/sb/js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="${pageContext.request.contextPath}/static/sb/js/plugins/morris/raphael.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/sb/js/plugins/morris/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/sb/js/plugins/morris/morris-data.js"></script>

    <!-- Flot Charts JavaScript -->
    <!--[if lte IE 8]><script src="js/excanvas.min.js"></script><![endif]-->
    <script src="${pageContext.request.contextPath}/static/sb/js/plugins/flot/jquery.flot.js"></script>
    <script src="${pageContext.request.contextPath}/static/sb/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/sb/js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="${pageContext.request.contextPath}/static/sb/js/plugins/flot/jquery.flot.pie.js"></script>
    <script src="${pageContext.request.contextPath}/static/sb/js/plugins/flot/flot-data.js"></script>

</body>

</html>
