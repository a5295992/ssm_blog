package com.along.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.along.annotation.ControllerLog;
import com.along.common.LocationConstant;
import com.along.common.Page;
import com.along.entity.Article;
import com.along.entity.ArticleExample;
import com.along.entity.Blog;
import com.along.entity.BlogExample;
import com.along.entity.Menu;
import com.along.entity.MenuExample;
import com.along.serviceImpl.ArticleServiceImpl;
import com.along.serviceImpl.BlogServiceImpl;
import com.along.serviceImpl.MenuServiceImpl;
@Controller
public class IndexController extends BaseController{
	private Logger  log = Logger.getLogger(IndexController.class);
	@Autowired
	private BlogServiceImpl blogService;
	
	@Autowired
	private ArticleServiceImpl articleService;
	
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@ControllerLog(description="访问前台主页")
	@RequestMapping(value="/f/index")
	public ModelAndView f_index(){
		log.info("从 配置文件中获取的变量："+adminPath);
		//ApplicationContext app = SpringContextHolder.getApplicationContext();
		
		return new ModelAndView(LocationConstant.f_index);
	}
	
	/**
	 * @return
	 */
	@ControllerLog(description="访问后台主页")
	@RequestMapping(value="/a/index")//后台主页
	public ModelAndView a_index(){
		MenuExample f = new MenuExample();
		f.createCriteria().andPidEqualTo("0");
		List<Menu> menuList = menuServiceImpl.getList(f ,true);
		return new ModelAndView(LocationConstant.a_index).addObject("menuList", menuList);
	}
	
	
	/**个人博客首页 
	 * @return
	 */
	@ControllerLog(description="个人博客主页")
	@RequestMapping(value="/f/blog/{userId}/index.html")//后台主页
	public ModelAndView a_blog_index(@PathVariable String userId,HttpServletRequest request){
		//查询博客 开通状态 是否开通博客
		BlogExample example = new BlogExample();
		example.setQueryClause("status,id");
		example.createCriteria().andCreateByEqualTo(userId);
		Blog blog = blogService .getSinge(example);
		
		if(blog.getStatus()==0){
			//博客未开通 默认不开通
			return new ModelAndView(LocationConstant.error404).addObject("errorMessage","该用户未开通博客或已停用");
		}
		//
		String blogId = blog.getId();
		
		ArticleExample f = new ArticleExample();
		f.setQueryClause("id,name,category,create_by,create_date,comments,shares,goods,content,category_id");
		//查询  博客所有文章按  时间排序 并分页
		getExample(request, f);
		f.createCriteria().andBlogIdEqualTo(blogId);//查询 该博客下所有 文章  忽略 分类
		Page<Article> page = articleService.getpage(f.getPageCount(), f.getPageNum(), f);
		
		return new ModelAndView(LocationConstant.f_blog_index_view).addObject("articles",page)
				.addObject("userId",userId);
	}
	
	@RequestMapping(value="/f/ue")
	public String toUeEdit(){
		log.info("this is programer debug .............");
		return "admin_view/blog/ue_edit";
	}
}
