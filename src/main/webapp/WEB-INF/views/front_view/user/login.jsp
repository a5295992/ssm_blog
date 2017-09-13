<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../global/global.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="登录,后台,keyword3">
	<meta http-equiv="description" content="简单登录">
	<link rel="stylesheet" type="text/css" href="${statics}/view/front_view/login/css/login_01.css">
	<script type="text/javascript" src="${statics}/view/front_view/login/js/login_01.js"></script>
  </head>
  <body>
<%--    	
	<form:form action="${headpath }/a/user/add?login='login'" method="POST" commandName="user">
   		loginName:<form:input path="loginName"/><br>
   		name:<form:input path="name"/><br>
   		email:<form:input path="email"/><br>
   		phone:<form:input path="phone"/><br>
   		password:<form:input path="password" title="password"/><br>
   	</form:form>
 --%>   
	<div id="sky"></div>
	<div id="head"></div>
	<div id="middle">
		<form id="login_form" method="POST" action="${headpath }/f/user/query?method=login">
			<ul style="text-align: center;">
				<li style="font-size: 48px">随心博客管理</li>
				<li><input class="name" name="loginName" placeholder="请输入用户名" value="${user.loginName }"></li>
				
				<li><input type="password" name="password" class="pwd" placeholder="请输入密码"></li>
				<li id="verifyCode" style="display: block;">
					<input id="valid"
					name="validateImage" placeholder="请输入验证码" maxlength="5"> 
					<span id="validcode">
					 <img id="verifyCodePic" id="img_captcha"
						src="${headpath}/getValidateImage">
					</span> 
					<span id="changeimg"> 换一张 </span></li>
				<li><input id="login" value="立即登录" type="button"></li>
				<c:forEach items="${result.failMessage }" var="fail">
					<li><span id="err" style="display: inline-block;">${fail }</span></li>
				</c:forEach>
				<li><span id="err" style="display: inline-block;">${result.successMessage}</span></li>
			</ul>
		</form>
	</div>
	<div id="footer">
		<a>关于我们@qq50330690 &nbsp &nbsp|&nbsp &nbsp</a> <a>Copyright © 2015
			宋安伟 保留所有权利 沪ICP备110号-1</a>
	</div>
	<div id="cloud"></div>
  </body>
  <script type="text/javascript">
  </script>
</html>
