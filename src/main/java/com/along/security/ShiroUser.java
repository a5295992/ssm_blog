package com.along.security;

import java.util.Date;

public class ShiroUser {
	
	private String userId;
	private String loginName;
	private String photo;
	private String loginIp;
	private Date   loginDate;
	private String roleName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public ShiroUser(String userId, String loginName, String photo,
			String loginIp, Date loginDate, String roleName) {
		super();
		this.userId = userId;
		this.loginName = loginName;
		this.photo = photo;
		this.loginIp = loginIp;
		this.loginDate = loginDate;
		this.roleName = roleName;
	}
	public ShiroUser() {
		super();
	}
	
	
}
