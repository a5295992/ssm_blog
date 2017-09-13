package com.along.controller;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.along.common.AnRequest;
import com.along.common.DateUtil;
import com.along.common.LocationConstant;
import com.along.entity.LogExample;
import com.along.entity.LogExample.Criteria;
import com.along.serviceImpl.LogServiceImpl;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class  LogController extends BaseController{
	
	@Autowired
	private LogServiceImpl logService;
	@ResponseBody
	@RequestMapping(value=AnRequest.A_LOG_ADD, produces = "text/html;charset=UTF-8;")
		public  String a_log_add(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_LOG_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String a_log_update(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_LOG_DELETE, produces = "text/html;charset=UTF-8;")
		public  String a_log_delete(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_LOG_QUERY, produces = "text/html;charset=UTF-8;")
		public  String a_log_query(HttpServletRequest request,HttpServletResponse response){
		//参数获取 
				String  data             = WebUtils.getCleanParam(request, "data");
				@SuppressWarnings("unchecked")
				Map<String,Object> param = (data==null)?new HashMap<String,Object>():JSONObject.fromObject(data);
				String  deleFlag         = WebUtils.getCleanParam(request, "delFlag");
				
				String createBy              = (String) param.get("createBy");
				String createDate            = (String) param.get("createDate");
				String url                   = (String) param.get("url");
				
				LogExample f = new LogExample();
				getExample(request, f);
				Criteria criteria = f.createCriteria();
				if(deleFlag !=null&&(deleFlag.equalsIgnoreCase("1")||deleFlag.equalsIgnoreCase("0"))){
					criteria.andDelFlagIsNotNull();//删除标记为0
				}else{
					criteria.andDelFlagEqualTo("0");//删除标记为0
				}
				//模糊查询
				criteria=createBy!=null?criteria.andCreateByEqualTo(createBy):criteria;
				criteria=createDate!=null?criteria.andCreateDateGreaterThan(DateUtil.toSqlDate(createDate)):criteria;
				criteria=url!=null?criteria.andUrlEqualTo(url):criteria;
				
				
				JSONObject page = logService.getPageToJSONFormatSqlDate(
						f.getPageCount(), f.getPageNum(), f);
				return page == null ? new JSONObject().toString() : page.toString();
						//封装参数
		}

	@RequestMapping(value=AnRequest.A_LOG_ADD_VIEW)
		public  ModelAndView a_log_add_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_LOG_UPDATE_VIEW)
		public  ModelAndView a_log_update_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_LOG_DELETE_VIEW)
		public  ModelAndView a_log_delete_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_LOG_QUERY_VIEW)
		public  ModelAndView a_log_query_view(HttpServletRequest request,HttpServletResponse response){
		
		
		
		return new ModelAndView(LocationConstant.a_log_query_view);
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_LOG_ADD, produces = "text/html;charset=UTF-8;")
		public  String f_log_add(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_LOG_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String f_log_update(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_LOG_DELETE, produces = "text/html;charset=UTF-8;")
		public  String f_log_delete(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_LOG_QUERY, produces = "text/html;charset=UTF-8;")
		public  String f_log_query(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_LOG_ADD_VIEW)
		public  ModelAndView f_log_add_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_LOG_UPDATE_VIEW)
		public  ModelAndView f_log_update_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_LOG_DELETE_VIEW)
		public  ModelAndView f_log_delete_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_LOG_QUERY_VIEW)
		public  ModelAndView f_log_query_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}


 }