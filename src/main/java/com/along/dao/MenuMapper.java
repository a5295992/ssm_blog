package com.along.dao;

import java.util.List;

import com.along.entity.Menu;
import com.along.entity.MenuExample;

public interface MenuMapper extends CrudDao<Menu, MenuExample> {
    
	
	  /**
     * 获取 子集
     * @param pid
     * @return
     */
    List<Menu> selectChildren(String pid);
    
    /**
     * 获取 父级
     * @param pid
     * @return
     */
    Menu selectParent(String pid);
}