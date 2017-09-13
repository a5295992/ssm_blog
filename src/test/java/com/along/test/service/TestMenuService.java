package com.along.test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.along.entity.Menu;
import com.along.entity.MenuExample;
import com.along.serviceImpl.MenuServiceImpl;

public class TestMenuService  {
	String path = "/spring-context.xml";
	ApplicationContext app;

	@Before
	public void setup() {
		app = new ClassPathXmlApplicationContext(path);
	}
	private MenuServiceImpl menuService;

	@Test
	public void testQuery() {
		menuService = app.getBean(MenuServiceImpl.class);
		MenuExample f = new MenuExample();
		f.createCriteria().andIdEqualTo("5");
		// 模糊查询

		Menu page = menuService.getSinge(f);
		System.out.println(page.getPmenu().getName());

	}
}
