package com.along.serviceImpl;

import org.springframework.stereotype.Service;

import com.along.annotation.ServiceLog;
import com.along.dao.UserMapper;
import com.along.entity.User;
import com.along.entity.UserExample;

@Service
public class UserServiceImpl extends CrudService<UserMapper, User,UserExample> {
	
	@ServiceLog(description="用户添加操作")
	@Override
	public int add(User t) {
		t.setCreateBy(t.getLoginName());
		return super.add(t);
	}
	
	@Override
	@ServiceLog(description="获取单个用户")
	public User getSinge(UserExample f) {
		// TODO Auto-generated method stub
		return super.getSinge(f);
	}
	
	@ServiceLog(description="用户修改操作")
	@Override
	public int update(User t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}
}
