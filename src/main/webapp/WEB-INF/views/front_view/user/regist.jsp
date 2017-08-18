<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../global/global.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
    	
	<form:form action="${headpath }/f/user/add?method=regist" method="POST" commandName="user">
		<table>
			<tr>
				<td>${message }</td>
				<td>${error }</td>
			</tr>
			<tr>
				<td>登录名:</td>
				<td><form:input path="loginName"/></td>
			</tr>
			<tr>
				<td>姓名:</td>
				<td><form:input path="name"/></td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><form:input path="email"/></td>
			</tr>
			<tr>
				<td>手机:</td>
				<td><form:input path="phone"/></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><form:password path="password"/></td>
			</tr>
			<tr>
				<td><button type="submit">提交</button></td>
			</tr>
		</table>
   	</form:form>
  </body>
</html>
