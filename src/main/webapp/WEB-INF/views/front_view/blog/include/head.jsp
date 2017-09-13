<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../global/global.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <head>
    <title>head</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="头部jsp">
	<link rel="stylesheet" href="${headpath }/statics/bootstrap/css/bootstrap.min.css">
    <script src="${headpath }/statics/bootstrap/js/jquery-2.1.4.min.js"></script>
    <script src="${headpath }/statics/bootstrap/js/bootstrap.min.js"></script> 
	<style>
  	.font_s{
  		color:gray;
  		text-align: center;
  	}
  </style>
  </head>
  
	<!--网站logo-->
	<div class="row">
        <div class="col-md-4">
       		 <img src="${headpath }/statics/bootstrap/img/blog.jpg" alt="logo" class="img-responsive">
        </div>
        <!--天气插件-->
        <div class="col-md-8"> <iframe style="float: right;" width="420" scrolling="no" height="60" frameborder="0" allowtransparency="true" 
        src="http://i.tianqi.com/index.php?c=code&amp;id=12&amp;icon=1&amp;num=5"></iframe></div>
    </div>
	
	
    
 <!-- 导航栏菜单start -->
 	<div class="row">
 		<div class="col-md-12">
            <nav class="navbar navbar-default"> 
        
                <div class="container">
        
                    <div class="navbar-header">
                        <a href="#" class="navbar-brand">博客</a>
                    </div>
                        <ul class="nav navbar-nav">
                        
                            <li class="active"><a href="#">首页</a></li> 
                            <li><a href="#">资讯</a></li>
                            <li ><a href="#">产品</a></li> 
                            <li><a href="#">关于</a></li>
                            
                            
                        </ul>
                     <div class="navbar-header">
                      <!-- 查询 接口-->
                        <form action="" class="navbar-form navbar-right"> 
                                <div class="input-group">
        
                                    <input type="text" class="form-control" placeholder="请输入查询关键词">
                                 <span class="input-group-btn">
                                
                                    <button type="submit" class="btn btn-default">查询</button>
                                 </span>
                                
                                </div>
                    
                        </form>
                      </div>   
                     
                        
                  </div>
                    
                   
        
            </nav>
     	 </div>
     </div>
    <!-- 导航栏菜单end -->
  
