package com.along.serviceImpl;

import org.springframework.stereotype.Component;

import com.along.dao.BlogMapper;
import com.along.entity.Blog;
import com.along.entity.BlogExample;

@Component
public class BlogServiceImpl extends CrudService<BlogMapper, Blog, BlogExample> {

	 
}
