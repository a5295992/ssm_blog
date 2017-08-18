package com.along.serviceImpl;

import org.springframework.stereotype.Service;

import com.along.dao.UserMapper;
import com.along.entity.User;
import com.along.entity.UserExample;

@Service
public class UserServiceImpl extends CrudService<UserMapper, User,UserExample> {
	
	@Override
	public int add(User t) {
		t.setCreateBy(t.getLoginName());
		return super.add(t);
	}
}
