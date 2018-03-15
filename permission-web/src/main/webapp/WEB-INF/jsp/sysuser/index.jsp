<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
 	<base href="<%=basePath %>">
	<meta charset="UTF-8">
	<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css">
	<link rel="stylesheet"type="text/css" href= "easyui/themes/icon.css">
	<script type="text/javascript" src = "easyui/jquery.min.js"></script>
	<script type="text/javascript" src = "easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src = "easyui/locale/easyui-lang-zh_CN.js"></script>
	
	</head>
<body>
	
	<style type="text/css">
		 
		#condition{
			padding:15px 10px;
		}
		.item{
			padding: 10px 10px;
		}
		.item>span{
			display: inline-block;
			width:100px;
			text-align: right;
			margin-right: 10px;
		}
	</style>
	<script type="text/javascript" >
	//重置按钮点击事件
	function reset(){
		$("#form1").form("clear");
		
	}
	//修改用户点击事件
	function edit_user(){
		var row = $("#dataGrid").datagrid("getSelected");
		if (row == null) {
			alert("请选择用户！");
			return;
		}
		//选中多个只保留最先选中的
		$("#dataGrid").datagrid("clearSelections");
		$("#dataGrid").datagrid("selectRecord",row.id);
		var d = $("<div></div>").appendTo("body");
		d.dialog({
			title:'修改用户',
			width:500,
			height:'auto',
			modal:true,
			href:'user/form',
			onClose:function (){ $(this).dialog("destroy"); },
			onLoad:function (){ 
				$.post("user/getUserById",{id:row.id},function (data){
					$("#userForm").form("load",data);
					var rids = new Array();
					$.each(data.roles,function (){
						rids.push(this.id); 
					});
					$("#rids").combobox("setValues",rids);
				})
			},
			buttons:[{
				iconCls:"icon-ok",
				text:"确定",
				handler:function(){
					$("#userForm").form("submit",{
						url : "user/edit",
						success : function(data){ 
							d.dialog("close");
							$("#dataGrid").datagrid("reload");
						}
					});
				}
			},{
				iconCls:"icon-cancel",
				text:"取消",
				handler:function(){
					d.dialog("close");
				}
			}]
			 
		})
		
	}
	//添加用户
	function add_user(){
		var d = $("<div></div>").appendTo("body");
		d.dialog({
			title:'添加用户',
			width:500,
			height:'auto',
			modal:true,
			href:'user/form',
			onClose:function (){ $(this).dialog("destroy"); },
			buttons:[{
				iconCls:'icon-ok',
				text:'确定',
				handler:function (){
					alert("123");
					//提交表单添加数据
					$("#userForm").form("submit",{
						url:'user/add',
						success:function (data){ 
							alert("添加成功!"); 
							$("#dataGrid").datagrid("reload");
							d.dialog("close");
						}
					}); 
				}
			}, {
				iconCls:'icon-cancel',
				text:'取消',
				handler:function (){
					d.dialog("close");
				}
				
			}]
		})
	}
		//删除(批量删除的点击事件)
	function delete_user(){ 
		//获取选中行
		var ids = $("#dataGrid").datagrid("getSelections");
		if (ids.length == 0) {
			$.messager.alert("提示","请选择要删除的行！","warning");
			return;
		}
		$.messager.confirm("提示","确定要删除么?",function (r){
			if (r) {
				var idStr = "ids=" + ids[0].id; 
				for (var i = 1; i < ids.length; i++) {
					idStr += "&ids=" + ids[i].id;
				} 
				$.post("user/batchDelete",idStr,function (data){
					if (data.result == true) {
						$("#dataGrid").datagrid("reload");
						//document.body.scrollTop+document.documentElement.scrollTop
						alert("删除成功！");
					}
				})
			}
		}) 
	}
	//自定义弹框方法
	function alert(message){
		$.messager.show({
			title:'提示信息',
			msg:message,
			timeout:1000,
			showType:'slide',
			showSpeed:200,
			style:{
				right:'1000',
				top:0,
				bottom:''
			}
		})
		
	}
	$(function (){
		
		//combobox组件
	$("#roles").tagbox({
		width:'auto',
		hasDownArrow:true,
		panelMinWidth:150
	});
		//dataGrid表格
		$("#dataGrid").datagrid({
			url:"user/list",
			rownumbers:true,
			striped:true,
			fitColumns:true,
			iconCls:"icon-serach",
			pagination:true,
			toolbar:"#tb",
			idField:"id"
		});
	}) 
	//角色拼接
	function roleFormatter(value,row,index){
		if (value.length > 0) {
			var str = "";
			for (var i = 0; i < value.length; i++) {
				str += "{" + value[i].name + "}  ";
			}
			return str;
		}else{
			return "-";
		}
		
	}
	//设置查询条件
	function setCondition(){ 
		var data = {username:$("#username").val()};
		
		var ids = $("#roles").combobox("getValues");
		for (var i = 0; i < ids.length; i++) {
			console.log(ids[i]);
			data["roles["+i+"].id"] = ids[i];
		}
		
		$("#dataGrid").datagrid("reload",data);
		
	}
	
	</script> 


${permissions }
<div id="condition" class = "easyui-panel" title = "查询条件">
	<form id = "form1">
		UserName : <input type="text" id = "username"/>
		Roles: 
		<input id="roles"  name="dept"
	    data-options="valueField:'id',textField:'name',url:'role/all',panelHeight:'auto',multiple:true">
	    <a id="btn" href="javascript:void(0)" onclick = "setCondition()" class="easyui-linkbutton" data-options="iconCls:'icon-sum'">查询</a>
		<a id="btn" href="javascript:void(0)" onclick = "reset()" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">撤销</a>
	</form>
</div>
<table id = "dataGrid" title= "User List">
	<thead>
		<th data-options = "field:'id',width:30,sortable:true,order:'desc'">Id</th>
		<th data-options = "field:'username',width:100">UserName</th>
		<th data-options = "field:'password',width:100">Password</th>
		<th data-options = "field:'salt',width:50">salt</th>
		<th data-options = "field:'roles',width:120,formatter:roleFormatter">Roles</th>
	</thead>
	<tbody>
	</tbody>
</table>
<div id="tb">
<c:if test="${fn:contains(permissions,'user:create') }">
<a href="javascript:void(0)" class="easyui-linkbutton" onclick = "add_user()" data-options="iconCls:'icon-add',plain:true">添加</a>
</c:if>
<c:if test="${fn:containsIgnoreCase(permissions,'user:update') }">
<a href="javascript:void(0)" class="easyui-linkbutton" onclick = "edit_user()" data-options="iconCls:'icon-edit',plain:true">修改</a>
</c:if>
<c:if test="${fn:containsIgnoreCase(permissions,'user:delete')}">
<a href="javascript:void(0)" class="easyui-linkbutton" onclick = "delete_user()" data-options="iconCls:'icon-remove',plain:true">删除</a>
</c:if>
<c:if test="${fn:containsIgnoreCase(permissions,'user:import')}">
<a href="javascript:void(0)" class="easyui-linkbutton" onclick = "export_user()" data-options="iconCls:'icon-sum',plain:true">导出</a>
</c:if>
</div>

	
</body>
</html>