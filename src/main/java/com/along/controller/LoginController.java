package com.along.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.along.common.CacheUtil;
import com.along.common.MD5Encode;
import com.along.entity.LogFlag;
import com.along.entity.User;
import com.along.entity.UserExample;
import com.along.security.ShiroUser;
import com.along.serviceImpl.UserServiceImpl;

@Component
public class LoginController {
	@Autowired
	private UserServiceImpl userService;
	private static Logger log = Logger.getLogger(UserController.class);
	public String login(HttpServletRequest request,HttpServletResponse response) {
		//获取 请参数
		String userName_p      = "loginName";
		String password_p      = "password";
		String validateImage_p = "validateImage";
		
		String userName        = WebUtils.getCleanParam(request, userName_p );//用户名 loginName
		String password        = WebUtils.getCleanParam(request, password_p ); //密码
		String validateImage   = WebUtils.getCleanParam(request, validateImage_p );//验证码
		String validateImageFromSession = (String) request.getSession().getAttribute("validateImage");//本地验证码
		/*log.info("1:"+userName);
		log.info("2:"+password);
		log.info("3:"+validateImage);
		log.info("4:"+validateImageFromSession);*/
		User userFromDataBase = null;
		String host = request.getLocalAddr();
		if(validateImage!= null&&validateImageFromSession!=null&&validateImage.equalsIgnoreCase(validateImageFromSession)){
			request.getSession().removeAttribute("validateImage");//移除session
			if(userName!=null&&password!=null){
				UserExample f =new UserExample();
				f .createCriteria().andLoginNameEqualTo(userName);
				userFromDataBase = userService.getSinge(f );
				boolean isUse = isUselly(userName,userFromDataBase);//用户是否可用
				if(isUse){
					Subject subject = SecurityUtils.getSubject();
					AuthenticationToken token = new UsernamePasswordToken(userName,MD5Encode.encode(password),host);
					try {
						subject.login(token );
					} catch (AuthenticationException e) {
						log.error(e);
						return "2:用户名或密码错误";
					}
				}else {
					return "2:用户不存在或已被限制";
				}
			}else {
				return "2:用户名或密码不能为空";
			}
		}else {
			return "2:验证码不正确";
		}
		userFromDataBase.setLoginIp(host);
		userService.update(userFromDataBase);
		return "0:登录成功";
	}
	
	private boolean isUselly(String userName,User userFromDataBase) {
		
		if(userFromDataBase==null){
			return false;
		}else{
			return LogFlag.INUSE.equals(userFromDataBase.getLoginFlag());
		}
		
	}
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	public String regist(HttpServletRequest request,HttpServletResponse response,User user){
		String default_result = "0:[user]"+user.getLoginName()+"regist success";
		//保存
		UserExample f =new UserExample();
		f .createCriteria().andLoginNameEqualTo(user.getLoginName());
		User userFromDataBase = userService.getSinge(f );
		if(userFromDataBase==null){
			user.setPassword(MD5Encode.encode(user.getPassword()));
			try {
				userService.add(user);
			} catch (Exception e) {
				default_result = "2:"+e.getCause().getMessage().substring(0, 10);
				log.error(e);
			}
		}else{
			return "2:登录名重复";
		}
		return default_result;
	}
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @return
	 */
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		if(shiroUser != null ){
			CacheUtil.remove("userCache","currentUser",shiroUser);
			subject.logout();
		}else {
			return "2:用户已退出或未登录";
		}
				
		return "0:"+shiroUser .getLoginName()+"[登出成功]";
	}
}
