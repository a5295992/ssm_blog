package com.along.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Component;

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
}
