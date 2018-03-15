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
	<title>Title</title>
</head>
<body>
	<form id = "userForm" method="post">
		<div class = "item">
			<input type="hidden" value="0" name = "id"/>
			 <span>userName:  </span><input type="text" name = "username" />
		</div>
		<div class = "item">
			 <span>password:  </span><input type="password" name = "password" />
		</div>
		<div class = "item">
			 <span>salt:  </span><input type="text" name = "salt" />
		</div>
		<div class = "item">
			 <span>locked:  </span><input name = "locked" value="1" class="easyui-switchbutton" data-options="onText:'是',offText:'否'">
		</div>
		<div class = "item">
			 <span>roles:  </span><input id ="rids"  class = "easyui-tagbox"  name="rids"
                  data-options="valueField:'id',textField:'name',url:'role/all',panelHeight:'auto',multiple:true,hasDownArrow:true,panelMinWidth:150">
		</div>
	</form>
</body>
</html>