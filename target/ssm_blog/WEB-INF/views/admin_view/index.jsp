<%@page import="com.along.common.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="../global/global.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台管理首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="后台管理首页">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
  </head>
  <style>
  	.font_s{
  		color:gray;
  		text-align: center;
  	}
  </style>
  <body class="easyui-layout">
   <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
       				<a href="${headpath }/f/user/query?method=logout" > 退出登录</a> &nbsp; &nbsp;随心博客管理
        </div>
		<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;"><p class="font_s">服务器时间 :[<%=DateUtil.toSqlDate() %>] </p></div>
		<div data-options="region:'east',split:true" title="消息管理" style="width:180px;">
			<ul class="easyui-tree" data-options="url:'${headpath}/a/roless',method:'get',animate:true,dnd:true"></ul>
		</div>
		<div data-options="region:'west',split:true" title="功能" style="width:200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
			
				<c:forEach items="${menuList }" var="menu">
					<div title="${menu.name }" style="padding:10px;">
					<c:forEach items="${menu.children }" var="menu_childern">
						<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search"
	                	onclick="addTab('${menu_childern.name}','${headpath}${menu_childern.href}')"> ${menu_childern.name}</a><br>
					</c:forEach>
                	</div>
				</c:forEach>
			</div>
		</div>
		<div data-options="region:'center',title:'分页面板',iconCls:'icon-ok'">
			<div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tt">
				<div title="欢迎页" data-options="closable:'true'" style="padding:10px">
					
					<p class="font_s">欢迎您:&nbsp&nbsp<shiro:principal property="loginName"></shiro:principal></p>
					
					<p class="font_s">您上次登录的时间是:&nbsp&nbsp<shiro:principal property="loginDate"></shiro:principal></p>
					<p class="font_s">您上次登录的IP是:&nbsp&nbsp<shiro:principal property="loginIp"></shiro:principal></p>
					<p class="font_s">如果不是您亲自登录的，请<a href="javascript:void(0)">[修改密码]</a> &nbsp&nbsp 或  &nbsp&nbsp<a href="javascript:void(0)">[锁定地区登录]</a></p>
				</div>
									
			</div>
		</div>
  </body>
  <script type="text/javascript" src="${statics }/view/admin_view/index.js"></script>
</html>
