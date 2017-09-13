package com.along.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.along.annotation.ControllerLog;
import com.along.common.AjaxResult;
import com.along.common.AnRequest;
import com.along.common.LocationConstant;
import com.along.common.MD5Encode;
import com.along.common.UserUtils;
import com.along.entity.User;
import com.along.entity.UserExample;
import com.along.entity.UserExample.Criteria;
import com.along.security.ShiroUser;
import com.along.serviceImpl.UserServiceImpl;
@Controller
public class  UserController extends LoginController {
	private static final String STARTSTRING = "******";
	@Autowired
	private UserServiceImpl userService;
	private static Logger log = Logger.getLogger(UserController.class);
	
	@ControllerLog(description="后台用户增删改操作")
	@ResponseBody
	@RequestMapping(value=AnRequest.A_USER_ADD, produces = "text/html;charset=UTF-8;")
		public  String a_user_add(HttpServletRequest request,HttpServletResponse response){
		
		String users = WebUtils.getCleanParam(request, "users");
		log.info("users : " + users);
		AjaxResult ajaxResult   = new AjaxResult();// 操作数据返回结果集
		
		if (users != null) {
			List<User>  list = getBeanListFormJsonString(users, User.class);// 将字符串集转为对象集
			md5pas(list);
			if (beanValidatorss(list, ajaxResult)) {// 数据验证
				ajaxResult.setSuccessCount(userService.update(list));
			}
		}
		return JSONObject.fromObject(ajaxResult).toString();
	}
	/**
	 * 加密 密码
	 * @param list
	 */
	private void md5pas(List<User> list) {
		for (User user : list) {
			if(user.getPassword()!=null&&!user.getPassword().equals(STARTSTRING)){
				user.setPassword(MD5Encode.encode(user.getPassword()));
				//退出用户
			}else {
				user.setPassword(null);
			}
		}
	}
	
	@ControllerLog(description="后台个人信息修改操作")
	@ResponseBody
	@RequestMapping(value=AnRequest.A_USER_UPDATE, produces = "text/html;charset=UTF-8;")
		public  String a_user_update(HttpServletRequest request,HttpServletResponse response){
		
		ShiroUser user = UserUtils.getUser();
		if(user==null){
			return "logout";
		}
		UserExample f = new UserExample();
		Criteria criteria = f.createCriteria();
		String filed =user.getPassword();
		criteria.andPasswordEqualTo(filed); 
		criteria = (user!=null)?criteria.andLoginNameEqualTo(user.getLoginName()):criteria;
		User userf     = userService.getSinge(f);
		if(userf==null){
			SecurityUtils.getSubject().logout();
			return "logout";
		}
		return "no";
		}

	@ResponseBody
	@RequestMapping(value=AnRequest.A_USER_DELETE, produces = "text/html;charset=UTF-8;")
		public  String a_user_delete(HttpServletRequest request,HttpServletResponse response){
		
		
		return null;
		}
	@ControllerLog(description="后台用户信息查询操作")
	@ResponseBody
	@RequestMapping(value=AnRequest.A_USER_QUERY, produces = "text/html;charset=UTF-8;")
		public  String a_user_query(HttpServletRequest request,HttpServletResponse response,Model model){
		//参数获取 
		String  data             = WebUtils.getCleanParam(request, "data");
		@SuppressWarnings("unchecked")
		Map<String,Object> param = (data==null)?new HashMap<String,Object>():JSONObject.fromObject(data);
		String  deleFlag         = WebUtils.getCleanParam(request, "delFlag");
		
		String name              = (String) param.get("name");
		String loginName         = (String) param.get("loginName");
		String id                = (String) param.get("id");
		String roleId            = (String) param.get("roleId");
		
		String password          = (String) param.get("password");
		UserExample f = new UserExample();
		getExample(request, f);
		Criteria criteria = f.createCriteria();
		if(deleFlag !=null&&(deleFlag.equalsIgnoreCase("1")||deleFlag.equalsIgnoreCase("0"))){
			criteria.andDelFlagIsNotNull();//删除标记为0
		}else{
			criteria.andDelFlagEqualTo("0");//删除标记为0
		}
		//模糊查询
		criteria=name!=null?criteria.andNameLike("%"+name+"%"):criteria;
		criteria=loginName!=null?criteria.andLoginNameLike("%"+loginName+"%"):criteria;
		criteria=id!=null?criteria.andIdLike("%"+id+"%"):criteria;
		criteria=roleId!=null?criteria.andUserTypeEqualTo(roleId):criteria;
		criteria=password!=null?criteria.andPasswordEqualTo(MD5Encode.encode(password)):criteria;
		
		JSONObject page = userService.getPageToJSONFormatSqlDate(
				f.getPageCount(), f.getPageNum(), f);
		return page == null ? new JSONObject().toString() : page.toString();
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
		
		String query = WebUtils.getCleanParam(request, "query");// ?query = one
		ModelAndView modelAndView = null;
		if(query != null){
			modelAndView = query.equals("one")? getCurrentUserInfo():getAnotherUserInfo(request);
		}else {
			modelAndView = new ModelAndView(LocationConstant.a_user_query_view);//所有用户查询
		}
		
		return modelAndView;
		}
		private ModelAndView getAnotherUserInfo(HttpServletRequest request) {
			String loginName = WebUtils.getCleanParam(request, "loginName");
			String id = WebUtils.getCleanParam(request, "id");
			
			UserExample f = new UserExample();
			Criteria criteria = f.createCriteria();
			
			criteria = (loginName == null)?criteria:criteria.andLoginNameEqualTo(loginName);
			criteria = (id 	 	  == null)?criteria:criteria.andIdEqualTo(id);
			
			User user = userService.getSinge(f);
		return new ModelAndView(LocationConstant.a_user_query_view_single).addObject("user",user );
		}

		private ModelAndView getCurrentUserInfo() {
			ShiroUser suser = UserUtils.getUser();
			UserExample f = new UserExample();
			Criteria criteria = f.createCriteria();
			criteria = (suser!=null)?criteria.andIdEqualTo(suser.getUserId()):criteria;
			User user = userService.getSinge(f);
		return new ModelAndView(LocationConstant.a_user_query_view_single).addObject("user",user );
		}

	//ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff
	@ControllerLog(description="前台用户添加操作")
	@RequestMapping(value=AnRequest.F_USER_ADD, produces = "text/html;charset=UTF-8;",method=RequestMethod.POST)
		public  String f_user_add(HttpServletRequest request,HttpServletResponse response, User user,Model model){
		String method = WebUtils.getCleanParam(request, "method");
		if(method != null&& method.equals("regist")){
			if(beanValidator(model,user)){
				AjaxResult result = regist(request, response, user);
				if(result.getFailMessage()==null){
					return "redirect:"+AnRequest.F_USER_QUERY_VIEW+"?method=login";
				}else {
					model.addAttribute("result", result);
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
	
	@ControllerLog(description="前台用户登录及查询操作")
	@RequestMapping(value=AnRequest.F_USER_QUERY, produces = "text/html;charset=UTF-8;")
	public  String f_user_query(HttpServletRequest request,HttpServletResponse response,Model model){
	
	String method = WebUtils.getCleanParam(request, "method");
	//携带参数  method="login"
	AjaxResult result = null;
	if(method != null ){
		result = method.equals("login")?login(request, response,model):logout(request, response);
		if(result.getFailMessage()==null){
			return "redirect:/f/index";
		}else {
			model.addAttribute("result", result);
			return LocationConstant.f_user_login;
		}
	}else {
		//做普通查询
		log.info("UserController a_user_query : 普通查询");
	}
	return method;
	}
	
	
	@RequestMapping(value = "/getValidateImage")
	public void validateImage(HttpServletRequest req,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		super.validateImage(req, response);
	}
 }