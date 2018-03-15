<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
 	<base href="<%=basePath %>">
	<meta charset="UTF-8"> 
</head>
<body>
	<form id = "roleForm" method="post">
		<div class = "item">
			 <input type="hidden" value="0" name = "id"/>
			 <span>roleName:  </span><input type="text" name = "name" />
		</div>
		<div class = "item">
		     <span>locked:  </span><input name = "available" value="1" class="easyui-switchbutton" data-options="onText:'YES',offText:'NO'">
		</div>
		
	</form>
</body>
</html>