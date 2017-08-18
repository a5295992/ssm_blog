<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--全局  jsp引入配置 常用 js包 引入操作  -->
<!--jstl表达式  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--jstl 函数  -->
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<!--安全验证标签  -->
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!--http代称  schame|  (http)|serverName |IP  port 端口号 |contextPath |项目名-->
<c:set var ="schame" value="${pageContext.request.scheme}"></c:set>
<c:set var="serverName" value="${pageContext.request.serverName }"></c:set>
<c:set var="port" value="${pageContext.request.serverPort }"></c:set>
<c:set var="headpath" value="${pageContext.request.contextPath}"></c:set>

<!--静态资源  -->
<c:set var="statics" value="${headpath }/statics"></c:set>
<c:set var="statics_views" value="${statics }/views"></c:set>
<!--模块静态资源  -->
<c:set var="statics_views_modules" value="${statics_views}/modules"></c:set>

<!-- 加载 全局js -->

<script type="text/javascript" src="${statics }/jquery/jquery-2.1.1.js"></script>

<!--常用js 变量 -->
<script type="text/javascript">
	var headpath ="${headpath}";/*  全局 地址*/
	var statics="${statics}";   /*  全局静态文件访问地址 */
	var statics_views_modules ="${statics_views_modules}";
</script>
<script type="text/javascript" src="${headpath }/statics/global/global.js"></script>


