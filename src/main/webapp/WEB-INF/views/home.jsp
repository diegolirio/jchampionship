<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	 <title>Home</title>
	 <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	 <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.css" rel="stylesheet">		
</head>
<body>
	<div class="container">	
		<h1 class="text-danger">Hello world!</h1>
		<p>The time on the server is ${serverTime}.</p>
	
	</div>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
