<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

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

</head>

<body>

    <div class="container">
    
    
    
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
            
            	<br/>
				<div class="class_message">
                     <div class="alert alert-danger alert-dismissable">
						 <!-- <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button> -->
                         <span id="id_alert_message"></span>
                     </div>				
				</div>            
            
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Login</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" id="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Senha" name="senha" type="password" value="">
                                </div>
                                <div class="checkbox"> 
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Lembrar
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" id="id_login" value="Entrar" >
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- jQuery Version 1.11.0 -->
    <script src="${pageContext.request.contextPath}/static/sb2/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/sb2/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/static/sb2/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/static/sb2/js/sb-admin-2.js"></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/usuario-controller.js"></script>

	<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/string-common.js"></script>
	<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>

	<script type="text/javascript">
		$('.class_message').hide();
	</script>

</body>

</html>
