package com.along.test.service;

import java.util.List;

import org.junit.Test;

import com.along.entity.Article;
import com.along.entity.ArticleExample;
import com.along.serviceImpl.ArticleServiceImpl;

public class TestArticleService extends TestBaseSerice {
	private ArticleServiceImpl articleService;

	@Test
	public void testAdd() {

		articleService = app.getBean(ArticleServiceImpl.class);

		Article article = new Article();
		// 姓名
		article.setName("我的文章测试");
		// 类别
		article.setCategory("技术");

		articleService.add(article);
	}

	@Test
	public void testUpdate() {

		articleService = app.getBean(ArticleServiceImpl.class);
		Article article = new Article();
		// 姓名
		article.setName("我的文章测试");
		// 类别
		article.setCategory("技术");

		articleService.update(article);
	}

	@Test
	public void testQuery() {

		articleService = app.getBean(ArticleServiceImpl.class);

		ArticleExample example = new ArticleExample();
		example.setQueryClause("id,name,create_by,create_date,comments,shares,goods,content,category_id");
		example.createCriteria().andNameEqualTo("我的文章测试");
		List<Article> Articles = articleService.getList(example);

		for (Article article : Articles) {
			System.out.println(article.getContent());
		}

	}
}
