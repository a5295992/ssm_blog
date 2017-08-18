package com.along.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.along.common.AnRequest;
import com.along.common.LocationConstant;
import com.along.entity.User;
import com.along.serviceImpl.UserServiceImpl;
@Controller
public class  UserController extends BaseController {
	@Autowired
	private UserServiceImpl userService;
	private static Logger log = Logger.getLogger(UserController.class);
	@RequestMapping(value=AnRequest.A_USER_ADD, produces = "text/html;charset=UTF-8;")
		public  String a_user_add(HttpServletRequest request,HttpServletResponse response){
		
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_USER_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String a_user_update(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_USER_DELETE, produces = "text/html;charset=UTF-8;")
		public  String a_user_delete(HttpServletRequest request,HttpServletResponse response){
		
		
		return null;
		}

	@RequestMapping(value=AnRequest.A_USER_QUERY)
		public  String a_user_query(HttpServletRequest request,HttpServletResponse response,Model model){
		
		return null;
		}

	@RequestMapping(value=AnRequest.A_USER_ADD_VIEW,method=RequestMethod.GET)
		public  ModelAndView a_user_add_view(HttpServletRequest request,HttpServletResponse response){
		
		
		return null;
		}

	@RequestMapping(value=AnRequest.A_USER_UPDATE_VIEW)
		public  ModelAndView a_user_update_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_USER_DELETE_VIEW)
		public  ModelAndView a_user_delete_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.A_USER_QUERY_VIEW)
		public  ModelAndView a_user_query_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}
	//ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff
	@RequestMapping(value=AnRequest.F_USER_ADD, produces = "text/html;charset=UTF-8;",method=RequestMethod.POST)
		public  String f_user_add(HttpServletRequest request,HttpServletResponse response, User user,Model model){
		String method = WebUtils.getCleanParam(request, "method");
		if(method != null&& method.equals("regist")){
			if(beanValidator(model,user)){
				String result = regist(request, response, user);
				if(result.startsWith("0")){
					return "redirect:"+AnRequest.F_USER_QUERY_VIEW+"?method=login";
				}else {
					model.addAttribute("message", result.split(":")[1]);
					model.addAttribute("user", user);
					return LocationConstant.f_user_add;
				}
				
			}else {
				model.addAttribute("user", user);
				return LocationConstant.f_user_add;
			}
		}
		return "redirect:"+AnRequest.F_USER_ADD_VIEW+"?method=regist";
		}
	
	@RequestMapping(value=AnRequest.F_USER_ADD_VIEW,method=RequestMethod.GET)//进入注册页面
	public  ModelAndView f_user_add_view(HttpServletRequest request,HttpServletResponse response){
	String method = WebUtils.getCleanParam(request, "method");
	if(method !=null&&method.equals("regist")){
		return new ModelAndView(LocationConstant.f_user_add).addObject("user",new User());
	}
	return null;
	}
	
	@ResponseBody
	@RequestMapping(value=AnRequest.F_USER_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String f_user_update(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.F_USER_DELETE, produces = "text/html;charset=UTF-8;")
		public  String f_user_delete(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_USER_UPDATE_VIEW)
		public  ModelAndView f_user_update_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_USER_DELETE_VIEW)
		public  ModelAndView f_user_delete_view(HttpServletRequest request,HttpServletResponse response){
		return null;
		}

	@RequestMapping(value=AnRequest.F_USER_QUERY_VIEW)
		public  ModelAndView f_user_query_view(HttpServletRequest request,HttpServletResponse response){
		String method = WebUtils.getCleanParam(request, "method");
		if(method !=null&& method.equals("login")){
			return new ModelAndView(LocationConstant.f_user_login);
		}
		return new ModelAndView("query");
		}

	@RequestMapping(value=AnRequest.F_USER_QUERY, produces = "text/html;charset=UTF-8;")
	public  String f_user_query(HttpServletRequest request,HttpServletResponse response,Model model){
	
	String method = WebUtils.getCleanParam(request, "method");
	//携带参数  method="login"
	String result = success;
	if(method != null ){
		result = method.equals("login")?login(request, response):logout(request, response);
		if(result.startsWith("0")){
			return "redirect:/f/index";
		}else {
			model.addAttribute("error", result);
			return LocationConstant.f_user_login;
		}
	}else {
		//做普通查询
		log.info("UserController a_user_query : 普通查询");
	}
	return result;
	}
	
	
	@RequestMapping(value = "/getValidateImage")
	public void validateImage(HttpServletRequest req,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		super.validateImage(req, response);
	}
 }