package com.along.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.along.common.AnRequest;
import com.along.common.LocationConstant;
import com.along.common.Page;
import com.along.entity.Menu;
import com.along.entity.MenuExample;
import com.along.serviceImpl.MenuServiceImpl;
@Controller
public class  MenuController extends BaseController {
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@ResponseBody
	@RequestMapping(value=AnRequest.A_MENU_ADD, produces = "text/html;charset=UTF-8;")
		public  String a_menu_add(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_MENU_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String a_menu_update(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_MENU_DELETE, produces = "text/html;charset=UTF-8;")
		public  String a_menu_delete(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_MENU_QUERY, produces = "text/html;charset=UTF-8;")
		public  String a_menu_query(HttpServletRequest request,HttpServletResponse response){
		
		MenuExample f = new MenuExample();
		getExample(request, f);
		
		Page<Menu> page = menuServiceImpl.getpage(f.getPageCount(), f.getPageNum(), f);
		return page==null?new JSONObject().toString():JSONObject.fromObject(page).toString();
		}

	@RequestMapping(value=AnRequest.A_MENU_ADD_VIEW)
		public  ModelAndView a_menu_add_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_MENU_UPDATE_VIEW)
		public  ModelAndView a_menu_update_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_MENU_DELETE_VIEW)
		public  ModelAndView a_menu_delete_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_MENU_QUERY_VIEW)
		public  ModelAndView a_menu_query_view(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView(LocationConstant.a_menu_query_view);
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_MENU_ADD, produces = "text/html;charset=UTF-8;")
		public  String f_menu_add(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_MENU_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String f_menu_update(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_MENU_DELETE, produces = "text/html;charset=UTF-8;")
		public  String f_menu_delete(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_MENU_QUERY, produces = "text/html;charset=UTF-8;")
		public  String f_menu_query(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_MENU_ADD_VIEW)
		public  ModelAndView f_menu_add_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_MENU_UPDATE_VIEW)
		public  ModelAndView f_menu_update_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_MENU_DELETE_VIEW)
		public  ModelAndView f_menu_delete_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_MENU_QUERY_VIEW)
		public  ModelAndView f_menu_query_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}


 }