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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.along.annotation.ControllerLog;
import com.along.annotation.TestParams;
import com.along.common.AjaxResult;
import com.along.common.AnRequest;
import com.along.common.LocationConstant;
import com.along.entity.Blog;
import com.along.entity.BlogExample;
import com.along.entity.BlogExample.Criteria;
import com.along.serviceImpl.ArticleServiceImpl;
import com.along.serviceImpl.BlogServiceImpl;
@Controller
public class BlogController extends BaseController{
	private Logger  log = Logger.getLogger(BlogController.class);
	@Autowired
	private BlogServiceImpl blogService;
	
	@Autowired
	private ArticleServiceImpl articleService;
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="进入博客管理")
	@RequestMapping(value=AnRequest.A_BLOG_QUERY_VIEW)
	public ModelAndView a_blog_query_view(HttpServletRequest request){
		
		
		return new ModelAndView(LocationConstant.a_blog_list_view);
	}
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="后台获取博客列表")
	@RequestMapping(value=AnRequest.A_BLOG_QUERY, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String a_blog_query(HttpServletRequest request){
		//参数获取 
		String  data             = WebUtils.getCleanParam(request, "data");
		@SuppressWarnings("unchecked")
		Map<String,Object> param = (data==null)?new HashMap<String,Object>():JSONObject.fromObject(data);
		String  deleFlag         = WebUtils.getCleanParam(request, "delFlag");
		
		String name              = (String) param.get("name");
		
		//分组
		log.info("data :" +data);
		//封装参数
		BlogExample f = new BlogExample();
		getExample(request, f);
		Criteria criteria = f.createCriteria();
		
		if(deleFlag !=null&&(deleFlag.equalsIgnoreCase("1")||deleFlag.equalsIgnoreCase("0"))){
			criteria.andDelFlagIsNotNull();//删除标记为0
		}else{
			criteria.andDelFlagEqualTo("0");//删除标记为0
		}
		criteria=name!=null?criteria.andNameLike("%"+name+"%"):criteria;
		
		JSONObject page = blogService.getPageToJSONFormatSqlDate(f.getPageCount(), f.getPageNum(), f);
		return page==null?new JSONObject().toString():page.toString();		
		
	}
	
	@TestParams(methodType="GET",params="{}")
	@ControllerLog(description="后台博客增删改")
	@RequestMapping(value=AnRequest.A_BLOG_ADD)
	public String a_blog_add_update_delete(HttpServletRequest request){
		
		String urlEntitys = WebUtils.getCleanParam(request, "urlEntitys");

		AjaxResult ajaxResult = new AjaxResult();// 操作数据返回结果集
		if (urlEntitys != null) {
			List<Blog> list = getBeanListFormJsonString(urlEntitys,
					Blog.class);// 将字符串集转为对象集

			if (beanValidatorss(list, ajaxResult)) {// 数据验证
				ajaxResult.setSuccessCount(blogService.update(list));
			}
		}
		return JSONObject.fromObject(ajaxResult).toString();
	}
	
	
	
}
