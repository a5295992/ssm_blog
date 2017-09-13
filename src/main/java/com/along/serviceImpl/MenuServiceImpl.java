package com.along.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.along.annotation.ServiceLog;
import com.along.common.CacheUtil;
import com.along.dao.MenuMapper;
import com.along.entity.Menu;
import com.along.entity.MenuExample;
@Component
public class MenuServiceImpl extends CrudService<MenuMapper, Menu, MenuExample> {

	public List<Menu> getList(MenuExample f, boolean b) {
		try {
			return b?super.getList(f):CacheUtil.getCahes("sysCache","menu",Menu.class);
		}finally{
			CacheUtil.put("sysCache","menu", getList(f));
		}
	}
	
	@ServiceLog(description="菜单添加操作")
	@Override
	public int add(Menu t) {
		// TODO Auto-generated method stub
		return super.add(t);
	}
	@ServiceLog(description="菜单修改操作")
	@Override
	public int update(Menu t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}
}
