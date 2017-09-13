package com.along.serviceImpl;

import org.springframework.stereotype.Component;

import com.along.dao.CommentMapper;
import com.along.entity.Comment;
import com.along.entity.CommentExample;

@Component
public class CommentServiceImpl extends CrudService<CommentMapper, Comment, CommentExample> {

}
