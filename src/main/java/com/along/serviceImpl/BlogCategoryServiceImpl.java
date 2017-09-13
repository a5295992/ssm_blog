package com.along.serviceImpl;

import org.springframework.stereotype.Component;

import com.along.dao.BlogCategoryMapper;
import com.along.entity.BlogCategory;
import com.along.entity.BlogCategoryExample;
@Component
public class BlogCategoryServiceImpl extends CrudService<BlogCategoryMapper, BlogCategory, BlogCategoryExample>{
	
}
