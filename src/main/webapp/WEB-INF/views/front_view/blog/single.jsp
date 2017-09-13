<%@page import="com.along.common.UserUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>文章详情页-${article.name }</title>
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
	<div class="container">
		<%@ include file="include/head.jsp"%>

		<!--左边内容开始-->
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">博文</div>

					<div class="panel-body"><!-- panel-body start -->

								<a href="#"> <span>${article.category }</span></a><!-- 文章分类 -->
									<blockquote>
										<a href="#"> <span>${article.name }</span>  <!--文章 标题  -->
										</a>
									</blockquote>
									<header><p style="text-align:center"><strong>${article.name }</strong></p></header><!--文章信息标题 -->
                                	<header><p style="text-align:center">by along 2017-10-5</p></header><!-- 作者时间 -->
	                              	
	                              	<div >${article.content }</div><!-- 文章内容 -->
	                              	
									    <!-- 输出作者等信息 -->
								  <span style="float:right">
									  <font size="2" color="gray">
									       作者  ${article.createBy } 发表于 ${article.createDate } 
								                 阅读(${article.comments }) &nbsp&nbsp点赞(${article.goods })&nbsp&nbsp
								                 分享(${article.shares })&nbsp&nbsp评论(${article.comments })
									  </font>
								  </span>
								  
								  
					</div><!-- panel-body end -->

			<!--内容foot start-->
     				<!--上一篇 下一篇start-->
                      <nav >
                        
                          <ul class="pagination">
    
                            <li> <a href="#">上一篇 </a></li> 
                            <li> <a href="#">谁的青春不迷茫 </a></li> 
                            <li> <a href="#">下一篇</a></li> 
                            <li> <a href="#">我的青春我做主 </a></li> 
                    
                          </ul>
                        
                      </nav>
                       <!--上一篇 下一篇end-->
		</div>

		                         <!--评论 框-->
              <div class="col-md-13">
             	<p>请输入您的邮箱</p>
                <p>验证码</p>
                <textarea class="form-control" rows="3"></textarea>
                <button class="btn btn-primary" data-toggle="button" autocomplete="off">发表</button>
             	<hr />
             
               <div class="panel panel-default"> <div class="panel-heading">
                    热门评论
                    
                    </div>
                    
                    <div class="panel-body">
                     <ul class="list-group">

                        <li class="list-group-item">1.这是起始

							<span class="badge">回复10</span></li>
                        
                        <li class="list-group-item">2.这是第二条数据</li> <li class="list-group-item">3.这是第三排信息</li> <li class="list-group-item">4.这是末尾</li>
                    
                    </ul>
                    <p style="text-align:center">
                    	<button id="myButton" type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off"
                        
                        data-toggle="collapse" data-target="#content">
                    全部评论
                    
                    </button>

                    
                    </p>
                          
                          
                     <div class="collapse" id="content"> <div class="well">
                     
                     
                     	  <ul class="list-group">

                            <li class="list-group-item">
                            	<div class="panel panel-default">
                                <div class="panel-heading">
                                <span>#1</span> <span> 爱和谁的王小二:</span><span class="badge">回复10</span>&nbsp&nbsp;<a href="#myModal" data-toggle="modal" data-target="#myModal">查看</a>
                                
                                </div>
                                <div class="panel-body">
                                <p>
                                这么多人说的都是真这么多人说的都是真的人这么多人说的
                                都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人的人
                                这么多人说的都是真这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人的人
                                这么多人说的都是真这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人
                                
                                </p>
                                <hr />
                                <p style="padding-left:0px"><a href="#">劳泽</a><font size="2px" color="gray">回复:</font><a href="#">劳泽爱哭的小猫咪</a>:<br />
                                	你是不是那啥 智商有问题
                                </p>
                                </div>
                                
                                </div>
                            	
                                </li>
                                 
                            	
                                <li class="list-group-item">
                            	<div class="panel panel-default">
                                <div class="panel-heading">
                                <span>#1</span> <span> 爱和谁的王小二:</span><span class="badge">回复10</span>
                                
                                </div>
                                <div class="panel-body">
                                
                                这么多人说的都是真这么多人说的都是真的人这么多人说的
                                都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人的人
                                这么多人说的都是真这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人的人
                                这么多人说的都是真这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人
                                
                                </div>
                                
                                </div>
                            	
                                </li>
                                
                                
                                <li class="list-group-item">
                            	<div class="panel panel-default">
                                <div class="panel-heading">
                                <span>#1</span> <span> 爱和谁的王小二:</span><span class="badge">回复10</span>
                                
                                </div>
                                <div class="panel-body">
                                
                                这么多人说的都是真这么多人说的都是真的人这么多人说的
                                都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人的人
                                这么多人说的都是真这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人的人
                                这么多人说的都是真这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人
                                
                                </div>
                                
                                </div>
                            	
                                </li>
                                
                                
                                
                                <li class="list-group-item">
                            	<div class="panel panel-default">
                                <div class="panel-heading">
                                <span>#1</span> <span> 爱和谁的王小二:</span><span class="badge">回复10</span>
                                
                                </div>
                                <div class="panel-body">
                                
                                这么多人说的都是真这么多人说的都是真的人这么多人说的
                                都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人的人
                                这么多人说的都是真这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人的人
                                这么多人说的都是真这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人这么多人说的都是真的人
                                
                                </div>
                                
                                </div>
                            	
                                </li>
                    
                    	  </ul>
                      
                           <nav  style="text-align:center">
                    
                              <ul class="pagination">
        
                                <li><a href="#">上一页</a></li> 
                                <li><a href="#">1</a></li> 
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">下一页</a></li>
                        
                              </ul>
                            
                          </nav>
                        </div>
                        
                        </div>                               
                    </div>
                
                </div>
             </div>
            
             <!--评论结束-->
	</div>


	<!-- 左边内容结束-->

	<%@ include file="include/right.jsp"%>



	<!-- foot -->


	</div>
	<%@ include file="include/foot.jsp"%>
	</div>
</body>
</html>
