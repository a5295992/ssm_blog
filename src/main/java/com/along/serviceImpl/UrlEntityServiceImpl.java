package com.along.serviceImpl;

import org.springframework.stereotype.Component;

import com.along.annotation.ServiceLog;
import com.along.dao.UrlEntityMapper;
import com.along.entity.UrlEntity;
import com.along.entity.UrlEntityExample;
@Component
public class UrlEntityServiceImpl extends CrudService<UrlEntityMapper, UrlEntity, UrlEntityExample> {
	
	@ServiceLog(description="地址添加操作")
	@Override
	public int add(UrlEntity t) {
		// TODO Auto-generated method stub
		return super.add(t);
	}
	@ServiceLog(description="菜单修改操作")
	@Override
	public int update(UrlEntity t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}
}
