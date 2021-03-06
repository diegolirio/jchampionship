<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>QuartaShow.com - Erro interno no Servidor!</title> 

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/sb2/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/static/sb2/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/sb2/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/static/sb2/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery Version 1.11.0 -->
    <script src="${pageContext.request.contextPath}/static/sb2/js/jquery-1.11.0.js"></script>

</head>

<body>

    <div id="wrapper">

 		<jsp:include page="_menu2.jsp"></jsp:include>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12 ">
                
                
                		<h1 class="text-danger">500 - Oops! Erro no servidor!</h1>
                		<p><a href="${pageContext.request.contextPath}">
                			<span class="glyphicon glyphicon-share-alt"></span> Ir para p�gina principal
                		</a></p>
                    
                    	<div class="table-responsive">
							<table class="table table-condensed table-hover table-striped table-bordered" >
								<thead>
									<tr class="danger text-red" valign="top">
										<td width="40%"><b>Error:</b></td>
										<td class="class_exceptionMessage">${pageContext.exception}</td>
									</tr>
								</thead>
								<tbody>
									<tr class="success" valign="top">
										<td><b>URI:</b></td>
										<td class="class_uri">${pageContext.errorData.requestURI}</td>
									</tr>		
									<tr class="active" valign="top">
										<td><b>Status code:</b></td>
										<td class="class_statusCode">${pageContext.errorData.statusCode}</td>
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
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/sb2/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/static/sb2/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/static/sb2/js/sb-admin-2.js"></script>

</body>

</html>

