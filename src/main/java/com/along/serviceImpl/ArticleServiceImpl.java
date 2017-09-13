package com.along.serviceImpl;

import org.springframework.stereotype.Component;

import com.along.dao.ArticleMapper;
import com.along.entity.Article;
import com.along.entity.ArticleExample;
@Component
public class ArticleServiceImpl extends CrudService<ArticleMapper, Article, ArticleExample> {
	
}
