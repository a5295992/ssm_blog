package com.along.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.along.common.AnRequest;
@Controller
public class  RoleController {
	@ResponseBody
	@RequestMapping(value=AnRequest.A_ROLE_ADD, produces = "text/html;charset=UTF-8;")
		public  String a_role_add(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_ROLE_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String a_role_update(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_ROLE_DELETE, produces = "text/html;charset=UTF-8;")
		public  String a_role_delete(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_ROLE_QUERY, produces = "text/html;charset=UTF-8;")
		public  String a_role_query(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_ROLE_ADD_VIEW)
		public  ModelAndView a_role_add_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_ROLE_UPDATE_VIEW)
		public  ModelAndView a_role_update_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_ROLE_DELETE_VIEW)
		public  ModelAndView a_role_delete_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_ROLE_QUERY_VIEW)
		public  ModelAndView a_role_query_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_ROLE_ADD, produces = "text/html;charset=UTF-8;")
		public  String f_role_add(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_ROLE_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String f_role_update(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_ROLE_DELETE, produces = "text/html;charset=UTF-8;")
		public  String f_role_delete(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_ROLE_QUERY, produces = "text/html;charset=UTF-8;")
		public  String f_role_query(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_ROLE_ADD_VIEW)
		public  ModelAndView f_role_add_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_ROLE_UPDATE_VIEW)
		public  ModelAndView f_role_update_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_ROLE_DELETE_VIEW)
		public  ModelAndView f_role_delete_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_ROLE_QUERY_VIEW)
		public  ModelAndView f_role_query_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}


 }