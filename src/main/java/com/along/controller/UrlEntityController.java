package com.along.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.along.common.AnRequest;
import com.along.common.LocationConstant;
import com.along.common.Page;
import com.along.common.QueryCondition;
import com.along.entity.UrlEntity;
import com.along.entity.UrlEntityExample;
import com.along.serviceImpl.UrlEntityServiceImpl;
@Controller
public class  UrlEntityController extends BaseController{
	private static Logger log = Logger.getLogger(UrlEntityController.class);
	@Autowired
	private UrlEntityServiceImpl urlEntityServiceImpl;
	@ResponseBody
	@RequestMapping(value=AnRequest.A_URLENTITY_ADD, produces = "text/html;charset=UTF-8;",method=RequestMethod.POST)
		public  String a_urlentity_add(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_URLENTITY_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String a_urlentity_update(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_URLENTITY_DELETE, produces = "text/html;charset=UTF-8;")
		public  String a_urlentity_delete(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_URLENTITY_QUERY, produces = "text/html;charset=UTF-8;")
		public  String a_urlentity_query(HttpServletRequest request,HttpServletResponse response){
		
		UrlEntityExample f = new UrlEntityExample();
		getExample(request, f);
		
		Page<UrlEntity> page = urlEntityServiceImpl.getpage(f.getPageCount(), f.getPageNum(), f);
		return page==null?new JSONObject().toString():JSONObject.fromObject(page).toString();
		}
	
	@RequestMapping(value=AnRequest.A_URLENTITY_QUERY_VIEW)
	public  ModelAndView a_urlentity_query_view(HttpServletRequest request,HttpServletResponse response){
		
	return new ModelAndView(LocationConstant.a_urlentity_query_view);
	}
	
	@RequestMapping(value=AnRequest.A_URLENTITY_ADD_VIEW)
		public  ModelAndView a_urlentity_add_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_URLENTITY_UPDATE_VIEW)
		public  ModelAndView a_urlentity_update_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_URLENTITY_DELETE_VIEW)
		public  ModelAndView a_urlentity_delete_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}


		
 }