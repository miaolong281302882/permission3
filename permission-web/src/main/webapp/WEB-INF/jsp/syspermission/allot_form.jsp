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
<script type="text/javascript">
	 	$(function (){
	 		$.post("permission/getpermissions",{rid:${rid}},function(data){
	 			$("#allotTree").tree({
	 				url:'permission/list',
	 				checkbox:true,
	 				loadFilter:function(d){ 
	 					$.each(d,function (){
	 						$.each(this.children,function (){
	 							if ($.inArray(this.id,data) >= 0) {
	 							     this.checked = true;
								}
	 						})
	 					})
	 					return d;
	 				}
	 			})
	 		})
	 	})
	 </script>
	 
	<ul id = "allotTree"></ul>
</body>
</html>