<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/views/global/global.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>随心而动，静而后能安</title>
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
  <shiro:authenticated>
  		尊敬的:
  		<shiro:hasRole name="sys:admin">
  		管理员用户:<shiro:principal property="loginName"></shiro:principal><br>
  		<br><a href="#">后台管理</a><br>
  		</shiro:hasRole><br>
  		<a href="#">主页资讯</a><br>
  		<a href="#">发现</a><br>
  		<a href="#">消息</a><br>
  		<a href="#">好友</a><br>
  </shiro:authenticated>
  		<shiro:guest>
  			<% 
  				String head = request.getContextPath();
  			response.sendRedirect(head+"/f/index"); %>
  		</shiro:guest>
  </body>
</html>
