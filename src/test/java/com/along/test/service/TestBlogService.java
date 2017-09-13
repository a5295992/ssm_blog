package com.along.test.service;

import java.util.List;

import org.junit.Test;

import com.along.entity.Blog;
import com.along.entity.BlogExample;
import com.along.serviceImpl.BlogServiceImpl;

public class TestBlogService extends TestBaseSerice {
	private BlogServiceImpl blogService;

	@Test
	public void testAdd() {

		blogService = app.getBean(BlogServiceImpl.class);

		Blog blog = new Blog();
		// 姓名
		blog.setName("我的博客测试");
		// 类别
		blog.setCategory("技术");

		blogService.add(blog);
	}

	@Test
	public void testUpdate() {

		blogService = app.getBean(BlogServiceImpl.class);

		Blog blog = new Blog();
		blog.setId("04d61836f6774f3b94e559da14ea4a24");
		// 姓名
		blog.setName("我的博客测试一二三");
		// 类别
		blog.setCategory("技术");

		blogService.update(blog);
	}

	@Test
	public void testQuery() {

		blogService = app.getBean(BlogServiceImpl.class);

		BlogExample example = new BlogExample();
		String queryClause = "name,id";
		example.setQueryClause(queryClause);
		example.createCriteria().andNameEqualTo("测试博客");
		List<Blog> blogs = blogService.getList(example);

		System.out.println(blogs);
	}
}
