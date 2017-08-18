package com.along.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.along.security.ShiroUser;


public class UserUtils {

	/**
	 * 获取 当前 用户
	 * 
	 * @return 当 前用户实例 | null
	 */
	public static ShiroUser getUser() {
		Subject subject = SecurityUtils.getSubject();// 获取当前 shiro 用对象
		ShiroUser user = (ShiroUser) subject.getPrincipal();// 获取当前用户 (登录完成)
		return user;// 当前用户实例 | null
	}
}
