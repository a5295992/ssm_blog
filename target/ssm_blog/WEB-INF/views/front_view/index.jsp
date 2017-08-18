<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../global/global.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
  	.font_s{
  		color:gray;
  		text-align: center;
  	}
  </style>
  </head>
  <body>
  		<p class="font_s">用户身份：<shiro:principal  property="roleName"></shiro:principal>
  			<shiro:guest>游客!<a href="${headpath }/f/user/query/view?method=login"> 请登录</a>
  				&nbsp&nbsp   <a href="${headpath }/f/user/add/view?method=regist">  注册</a>
  			</shiro:guest>
  		</p>
  		<p class="font_s">欢迎来到主页！<shiro:principal property="loginName"></shiro:principal><a></a>
  		<p class="font_s"><a href="${headpath }/a/index"> 后台管理</a></p>
  </body>
</html>
