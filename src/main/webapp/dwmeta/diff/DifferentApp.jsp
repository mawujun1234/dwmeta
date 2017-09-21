<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String db_id=request.getParameter("db_id");
%>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="UTF-8">
	<script>
	window.db_id='<%=db_id%>'
	</script>
	
	<%@include file="../common/init.jsp" %>
	<script type="text/javascript" src="DifferentApp.js"></script>
	<style>
	.greennode{
		color:green;
	}
	.nodedeleted{
		color:red;
		text-decoration:line-through;
	}
	</style>
	
  </head>
  
  <body>
   
  </body>
</html>