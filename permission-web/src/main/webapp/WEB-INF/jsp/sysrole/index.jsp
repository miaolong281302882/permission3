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
	<title>角色列表</title>

	
	<link rel="stylesheet" type="text/css" href="easyui/themes/material/easyui.css">
	<link rel="stylesheet"type="text/css" href= "easyui/themes/icon.css">
	<script type="text/javascript" src = "easyui/jquery.min.js"></script>
	<script type="text/javascript" src = "easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src = "easyui/locale/easyui-lang-zh_CN.js"></script>
 </head>
<body>
	
	<script type="text/javascript">
	//分配权限按钮点击事件
	function allotPer(roleId){
		 $("#roleTable").datagrid("clearSelections");
		 //弹出模态框
		 var d = $("<div></div>").appendTo("body");
		 d.dialog({
			 title:'分配权限',
			 width:200,
			 height:300,
			 href:'role/toallot?rid=' + roleId,
			 modal:true,
			 onClose:function (){
				 $(this).dialog("destroy");
			 },
			 buttons:[
						 {
							 text:'确认',
							 iconCls:'icon-ok',
							 handler:function (){
								 //获取选中的节点checked indeterminate
								 var nodes = $("#allotTree").tree("getChecked","checked");
								 var half_nodes = $("#allotTree").tree("getChecked","indeterminate");
							     $.merge(nodes,half_nodes);
							     var postDate = "";
								 for (var i = 0; i < nodes.length; i++) {
									postDate += "ids="+nodes[i].id + "&"; 
								}
								 postDate += "rid=" + roleId;
								$.post("permission/allotPer",postDate,function (data){
									if (data.result) {
										$.messager.show({
											title:'提示',
											msg:'分配成功!', 
											timeout:'2000'
										});
									}
								}) 
								 d.dialog("close");
							 }
						 },
						 {
							 text:'取消',
							 iconCls:'icon-cancel',
							 handler:function (){
								 d.dialog("close");
							 }
						 }
			 		] 
		 })
		 
	}
	//自定义列
	function roleFormatter(value,row,index){
		return "<a class = 'opBtn' title = '分配权限' onclick = 'allotPer("+row.id+")' href = 'javascript:void(0)'><img src = 'easyui/themes/icons/large_chart.png' width='16'></img></a>";
	}
	
	//datagrid 组件
		$(function (){
			$("#roleTable").datagrid({
				title:'角色列表',
				iconCls:'icon-search',
				url:'role/list',
				rownumbers:true,
				idField:'id',
				striped:true,
				pagination:true,
				fitColumns:true,
				toolbar:"#tb", 
				onLoadSuccess:function (){
					$("a.opBtn").tooltip({
						position:'right'
					})
				}
			})
		})
	</script>
	 
	 <table id = "roleTable" >
	 	  <thead> 
	 	  	<th data-options = "field:'id',width:30">编号</th>
	 	  	<th data-options = "field:'name',width:60">角色名称</th>
	 	  	<th data-options = "field:'available',width:30">是否可用</th> 
	 	  	<th data-options = "field:'sas',width:30,formatter:roleFormatter">操作</th>
	 	  </thead>
	 </table>
	 
	 <div id="tb">
	 <c:if test="${fn:containsIgnoreCase(permissions,'role:create')}">
	 	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add_role();" data-options="iconCls:'icon-add',plain:true">添加</a>
	 </c:if>
	  <c:if test="${fn:containsIgnoreCase(permissions,'role:update')}">
	 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="edit_role();" data-options="iconCls:'icon-edit',plain:true">修改</a>
	 </c:if>
	  <c:if test="${fn:containsIgnoreCase(permissions,'role:delete')}">
	 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="delete_role();" data-options="iconCls:'icon-remove',plain:true">删除</a>
	 </c:if>
	  <c:if test="${fn:containsIgnoreCase(permissions,'role:import')}">
	 		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">导出</a>
	 </c:if> 
	
	</div>

<style type="text/css">
		  
		#condition{
			padding:15px 10px;
		}
		#roleForm .item{
			padding: 10px 10px;
		}
		#roleForm .item>span{
			display: inline-block;
			width:100px;
			text-align: right;
			margin-right: 10px;
		} 
	</style>

<script type="text/javascript"> 
		//增删改的操作方法
		
		
		//删除
		function delete_role(){
			var rows = $("#roleTable").datagrid("getSelections");
			if (!rows.length > 0) {
				show("请选择用户！");
				return;
			}
			$.messager.confirm("提示","确定要删除么?",function (r){
				if (r) {
					var postData = "";
					$.each(rows,function (){
						postData += "ids="+this.id +"&";
					});
					console.log(postData);
					//点击了确定按钮,发送异步请求删除数据
					$.post("role/delete",postData,function(data){
						if (data.result) {
							show("删除成功！");
							$("#roleTable").datagrid("reload");
						}
					})
				}
			})
		}
		
		
		//底部提示框
		function show(msg){
			$.messager.show({
				title:'提示',
				msg:msg,
				timeout:2000,
				width:300,
				height:200
			});
		}
		//修改
		function edit_role(){
			//获取选中行
			var row = $("#roleTable").datagrid("getSelected");
			if (row == null) {
				show("请选择用户！");
				return;
			}
			
			$("#roleTable").datagrid("clearSelections");
			$("#roleTable").datagrid("selectRecord",row.id);
			
			
			var d = $("<div></div>").appendTo("body");
			d.dialog({
				title:'edit-role',
				width:500,
				height:'auto',
				modal:true,
				href:'role/role_form',
				onClose:function (){
					d.dialog("destroy");
				},
				 onLoad:function (){
					 $.post("role/selectRole",{rid:row.id},function (data){
						 $("#roleForm").form("load",data);
						 
					 })
				 },
				buttons:[
					{
						iconCls:'icon-ok',
						text:'确定',
						handler:function (){
						    $("#roleForm").form("submit",{
						    	url:'role/edit',
						    	success:function(data){
						    		console.log(data.result);
						    		//if (data.result){
										show("修改成功");
										$("#roleTable").datagrid("reload");
						    		//}
						    }});
							d.dialog("close");
						}
					},{
						iconCls:'icon-cancel',
						text:'取消',
						handler:function (){
							d.dialog("close");
						}
					}
				]
				
			});
		}
		
		
		//添加
		function add_role(){
			var d = $("<div></div>").appendTo("body");
			d.dialog({
				title:"add-role",
				width:500,
				height:'auto',
				modal:true,
				href:'role/role_form',
				onClose:function (){
					d.dialog("destroy");
				},
				buttons:[
					{
						iconCls:'icon-ok',
						text:'确定',
						handler:function (){
							$("#roleForm").form("submit",{
								url:'role/add',
								success:function (data){
									if (data.result) {
										d.dialog("close");
										//刷新表格
										$("#roleTable").datagrid("reload");
										$.messager.show({
											title:'提示',
											msg:'添加成功！',
											timeout:2000
										})
									}
								}
							})
							d.dialog("close");
						} 
					},{
						iconCls:'icon-cancel',
						text:'取消',
						handler:function (){
							d.dialog("close");
						}
					}
					
				]
			})
		}
	</script>
	</body>
</html>