package com.along.test.service;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBaseSerice {

	String path = "/spring-context.xml";
	ApplicationContext app;

	@Before
	public void setup() {
		app = new ClassPathXmlApplicationContext(path);
	}
}
