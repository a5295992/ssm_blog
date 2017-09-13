package com.along.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.along.annotation.ControllerLog;
import com.along.annotation.TestParams;
import com.along.common.AjaxResult;
import com.along.common.AnRequest;
import com.along.common.LocationConstant;
import com.along.common.Page;
import com.along.common.UserUtils;
import com.along.entity.Article;
import com.along.entity.ArticleCategory;
import com.along.entity.ArticleCategoryExample;
import com.along.entity.ArticleExample;
import com.along.entity.ArticleExample.Criteria;
import com.along.entity.Blog;
import com.along.entity.BlogExample;
import com.along.entity.Comment;
import com.along.entity.CommentExample;
import com.along.security.ShiroUser;
import com.along.serviceImpl.ArticleCategoryServiceImpl;
import com.along.serviceImpl.ArticleServiceImpl;
import com.along.serviceImpl.BlogServiceImpl;
import com.along.serviceImpl.CommentServiceImpl;
import com.along.serviceImpl.MenuServiceImpl;

@Controller
public class ArticleController extends BaseController {
	private Logger log = Logger.getLogger(ArticleController.class);
	@Autowired
	private CommentServiceImpl commentService;
	@Autowired
	private BlogServiceImpl blogService;

	@Autowired
	private ArticleServiceImpl articleService;
	@Autowired
	private ArticleCategoryServiceImpl articleCategoryService;

	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@TestParams(params="{}",methodType="GET")
	@ControllerLog(description = "前台获取文章详细信息")
	@RequestMapping(value = "/f/article/{userId}/{articleId}.html")
	public ModelAndView f_article_single(@PathVariable String userId, @PathVariable String articleId) {
		log.info("user ID :" +userId+ "article Id :" +articleId);
		// ApplicationContext app = SpringContextHolder.getApplicationContext();

		// 查询博客 开通状态 是否开通博客
		BlogExample example = new BlogExample();
		example.setQueryClause("status,id");
		example.createCriteria().andCreateByEqualTo(userId);
		Blog blog = blogService.getSinge(example);

		if (blog.getStatus() == 0) {
			// 博客未开通 默认不开通
			return new ModelAndView(LocationConstant.error404).addObject(
					"errorMessage", "该用户未开通博客或已停用");
		}
		
		
		ArticleExample f = new ArticleExample();
		f.setQueryClause("id,name,category,create_by,create_date,comments,shares,goods,content,category_id");
		f.createCriteria().andIdEqualTo(articleId);
		//是否公开
		Article article = articleService.getSinge(f );
		CommentExample commentExample = new CommentExample();
		commentExample.createCriteria().andArticleIdEqualTo(articleId);
		//热门评论
		Page<Comment> list = commentService.getpage(5, 0, commentExample);
		return new ModelAndView(LocationConstant.f_blog_article).addObject("article", article)
				.addObject("comments",list);
	}
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="进入文章管理")
	@RequestMapping(value=AnRequest.A_ARTICLE_QUERY_VIEW)
	public ModelAndView a_blog_query_view(HttpServletRequest request){
		
		
		return new ModelAndView(LocationConstant.a_article_list_view);
	}
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="后台获取文章列表")
	@RequestMapping(value=AnRequest.A_ARTICLE_QUERY, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String a_blog_query(HttpServletRequest request){
		//参数获取 
		String  data             = WebUtils.getCleanParam(request, "data");
		@SuppressWarnings("unchecked")
		Map<String,Object> param = (data==null)?new HashMap<String,Object>():JSONObject.fromObject(data);
		String  deleFlag         = WebUtils.getCleanParam(request, "delFlag");
		
		String name              = (String) param.get("name");
		log.info("data :" +data);
		//封装参数
		ArticleExample f = new ArticleExample();
		getExample(request, f);
		Criteria criteria = f.createCriteria();
		
		if(deleFlag !=null&&(deleFlag.equalsIgnoreCase("1")||deleFlag.equalsIgnoreCase("0"))){
			criteria.andDelFlagIsNotNull();//删除标记为0
		}else{
			criteria.andDelFlagEqualTo("0");//删除标记为0
		}
		criteria=name!=null?criteria.andNameLike("%"+name+"%"):criteria;
		
		JSONObject page = articleService.getPageToJSONFormatSqlDate(f.getPageCount(), f.getPageNum(), f);
		return page==null?new JSONObject().toString():page.toString();		
		
	}
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="后台文章增删改")
	@ResponseBody
	@RequestMapping(value=AnRequest.A_ARTICLE_ADD)
	public String a_blog_add_update_delete(HttpServletRequest request){
		
		String articles = WebUtils.getCleanParam(request, "articles");

		AjaxResult ajaxResult = new AjaxResult();// 操作数据返回结果集
		if (articles != null) {
			List<Article> list = getBeanListFormJsonString(articles,
					Article.class);// 将字符串集转为对象集

			if (beanValidatorss(list, ajaxResult)) {// 数据验证
				ajaxResult.setSuccessCount(articleService.update(list));
			}
		}
		return JSONObject.fromObject(ajaxResult).toString();
	}
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="后台文章编辑")
	@RequestMapping(value=AnRequest.A_ARTICLE_ADD_VIEW)
	public ModelAndView a_article_add_view(HttpServletRequest request){
		//根据当前用户获取博客 信息
		ShiroUser user = UserUtils.getUser();
		BlogExample f = new BlogExample();
		f.createCriteria().andCreateByEqualTo(user.getUserId());
		Blog blog = blogService.getSinge(f );
		
	return new ModelAndView(LocationConstant.a_article_add_view).addObject("blog",blog);
	}
	
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="进入文章类别管理")
	@RequestMapping(value=AnRequest.A_ARTICLE_CATEGORY_QUERY_VIEW)
	public ModelAndView a_article_category_query_view(HttpServletRequest request){
		
		
		return new ModelAndView(LocationConstant.a_article_category_list_view);
	}
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="后台获取文章类别列表")
	@RequestMapping(value=AnRequest.A_ARTICLE_CATEGORY_QUERY, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String a_article_category_query(HttpServletRequest request){
		//参数获取 
		String  all              = WebUtils.getCleanParam(request, "all");//当前用户
		String  data             = WebUtils.getCleanParam(request, "data");
		@SuppressWarnings("unchecked")
		Map<String,Object> param = (data==null)?new HashMap<String,Object>():JSONObject.fromObject(data);
		String  deleFlag         = WebUtils.getCleanParam(request, "delFlag");
		
		String name              = (String) param.get("name");
		log.info("data :" +data);
		//封装参数
		ArticleCategoryExample f = new ArticleCategoryExample();
		getExample(request, f);
		com.along.entity.ArticleCategoryExample.Criteria criteria = f.createCriteria();
		
		if(deleFlag !=null&&(deleFlag.equalsIgnoreCase("1")||deleFlag.equalsIgnoreCase("0"))){
			criteria.andDelFlagIsNotNull();//删除标记为0
		}else{
			criteria.andDelFlagEqualTo("0");//删除标记为0
		}
		criteria=(name!=null)?criteria.andNameLike("%"+name+"%"):criteria;
		criteria=(all==null)?criteria.andCreateByEqualTo(UserUtils.getUser().getUserId()):criteria;
		
		JSONObject page = articleCategoryService.getPageToJSONFormatSqlDate(f.getPageCount(), f.getPageNum(), f);
		return page==null?new JSONObject().toString():page.toString();		
		
	}
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="后台文章类别增删改")
	@RequestMapping(value=AnRequest.A_ARTICLE_CATEGORY_ADD)
	public String a_article_category_add_update_delete(HttpServletRequest request){
		
		String articleCategorys = WebUtils.getCleanParam(request, "articleCategorys");

		AjaxResult ajaxResult = new AjaxResult();// 操作数据返回结果集
		if (articleCategorys != null) {
			List<ArticleCategory> list = getBeanListFormJsonString(articleCategorys,
					ArticleCategory.class);// 将字符串集转为对象集

			if (beanValidatorss(list, ajaxResult)) {// 数据验证
				ajaxResult.setSuccessCount(articleCategoryService.update(list));
			}
		}
		return JSONObject.fromObject(ajaxResult).toString();
	}
}
