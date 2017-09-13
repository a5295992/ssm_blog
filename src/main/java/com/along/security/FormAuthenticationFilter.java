package com.along.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

@Service
public class FormAuthenticationFilter extends
		org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		System.out.println("hey ! ");
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null)
			password = "";

		return new UsernamePasswordToken(username, password);
	}

	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		return super.executeLogin(request, response);
	}

	protected void executeChain(ServletRequest request,
			ServletResponse response, FilterChain chain) throws Exception {
		super.executeChain(request, response, chain);
	}
	
}