package com.along.security;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.along.entity.Role;
import com.along.entity.User;
import com.along.entity.UserExample;
import com.along.serviceImpl.UserServiceImpl;
/**
 * 
 * @author Administrator
 * doc:
 */
@Component
public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserServiceImpl userService;
	private static Logger log = Logger.getLogger(MyRealm.class);
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
		log.info("身份验证: 当前登录用户["+user.getLoginName());
		Set<String> roles = new HashSet<String>();
		roles.add(user.getRole().getName());
		// 根据 用户名获取所有角色
		SimpleAuthorizationInfo infor = new SimpleAuthorizationInfo();
		log.info("身份验证: 当前登录用户身份["+user.getRole().getName());
		return infor;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		
		UsernamePasswordToken  token = (UsernamePasswordToken)arg0;
		
		String userName = token.getUsername();
		String password = new String(token.getPassword());
		UserExample userExample = new UserExample();
		userExample.createCriteria().andLoginNameEqualTo(userName)
		.andPasswordEqualTo(password);
		User userFormData = userService.getSinge(userExample );
		
		//
		SimpleAuthenticationInfo simple = null;
		if(userFormData != null){
			String userId = userFormData.getId();
			String loginName = userFormData.getLoginName();
			String photo = userFormData.getPhoto();
			String loginIp = token.getHost();
			Date loginDate = userFormData.getLoginDate();
			Role roleName = userFormData.getRole();
			ShiroUser shiroUser = new ShiroUser(userId, loginName, photo, loginIp, loginDate, roleName,password);
			simple = new SimpleAuthenticationInfo(shiroUser,password,getName() );
		}
		return simple;
	}

	
}