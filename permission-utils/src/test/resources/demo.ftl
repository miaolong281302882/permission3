<!DOCTYPE HTML>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title></title>
</head>
<body>
	hello,${name}
	
	<br/>电脑的配置信息<br/>
	<ul>
		<li>brand  :${computer.brand}</li>
		<li>price  :${computer.price}</li>
		<li>color  :${computer.color}</li> 
	</ul>
	<#if computer.price &gt;= 2000>
		有点贵
		<#else>
		便宜
	</#if>
	<h1>水果列表(循环标签)</h1>
	
	<br/>
	
	<#list list1 as item>
		${item} 
		<#if item_has_next>,</#if>
	</#list>
	
	<br/>
	<ul>
	<#list list1 as f>
		<li><div>${f}</div></li>
	<#else>
	I have no hobiess!
	</#list>
	</ul>
	
	
	
</body>
</html>