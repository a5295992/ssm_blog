package com.along.serviceImpl;

import org.springframework.stereotype.Component;

import com.along.dao.ArticleCategoryMapper;
import com.along.entity.ArticleCategory;
import com.along.entity.ArticleCategoryExample;
@Component
public class ArticleCategoryServiceImpl extends CrudService<ArticleCategoryMapper, ArticleCategory, ArticleCategoryExample>  {
	
}
