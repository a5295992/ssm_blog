<%@page import="com.along.common.UserUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人博客主页</title>
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

						<ul class="list-unstyled">
							<c:forEach items="${articles.rows }" var="article"><!-- 博文循环  start -->
								<li>
								<a href="#"> <span>${article.category }</span></a><!-- 文章分类 -->
									<blockquote>
										<a href="${headpath }/f/article/${userId}/${article.id}.html"> <span>${article.name }</span>  <!--文章 标题  -->
										</a>
									</blockquote>
									<div>${func:replace(article.content,"<img(.*?)>","...") }...</div><!-- 过滤后的文章内容 -->
									<br>
									<!-- 展示文章中的图片 -->
									<div class="container">
										<div class="row"><!-- row start -->
											<!--1  -->
											<c:forEach items="${func:cutString(article.content,'src=\"','\"') }" var ="img" end="3"><!-- 图片循环开始 -->
												<div class="col-xs-2 col-md-2 col-sm-2"> 
		                                            <div class="thumbnail">
		                                            <a href=#>  <img src="${headpath }/statics/bootstrap/img/pic.jpeg" alt=""> 
		                                                </a>
		                                            </div>
	                                        	</div>
                                        	</c:forEach>																			<!-- 图片循环结束 -->
                                        	
                                        </div><!-- row end -->
                                        
								    </div>
								    <!-- 输出作者等信息 -->
								  <span style="float:right">
									  <font size="2" color="gray">
									       作者  ${article.createBy } 发表于 ${article.createDate } 
								                 阅读(${article.comments }) &nbsp&nbsp点赞(${article.goods })&nbsp&nbsp
								                 分享(${article.shares })&nbsp&nbsp评论(${article.comments })
									  </font>
								  </span>
							</li>
							<hr />
					       </c:forEach><!-- 博文循环  end -->
						</ul>
					</div><!-- panel-body end -->

			<!--内容foot start-->

		</div>

		<!--分页开始-->
		<nav>

		<ul class="pagination">

			<li><a href="#">上一页</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">${articles.pageNum }</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">下一页</a></li>

		</ul>

		</nav>
	</div>


	<!-- 左边内容结束-->

	<%@ include file="include/right.jsp"%>



	<!-- foot -->


	</div>
	<%@ include file="include/foot.jsp"%>
	</div>
</body>
</html>
