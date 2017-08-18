package com.along.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.along.common.Page;
import com.along.dao.CrudDao;
import com.along.entity.DataEntity;
import com.along.entity.Example;
import com.along.service.BaseService;

public abstract class CrudService<G extends CrudDao<T,D >, T extends DataEntity<T>, D extends Example<D> > extends BaseService<T>{
	
	@Autowired
	protected  G dao;
	public int add(T t) {
		setInsertValue(t);
		return dao.insertSelective(t);
	}
	public int adds(List<T> list) {
		int result = 0;
		for (T t : list) {
			result = add(t);
		}
		return result;
	}
	
	public int delete(String id){
		return dao.deleteByPrimaryKey(id);
	}
	
	public int deletes(D example){
		return dao.deleteByExample(example);
	}
	
	public int update(T t){
		setUpdateValue(t);
		return dao.updateByPrimaryKeySelective(t);
	}
	
	public int updates(T t,D f){
		setUpdateValue(t);
		return dao.updateByExampleSelective(t, f);
	}
	public T getSinge(D f){
		List<T> list = getList(f);
		
		return (list !=null&&list.size()>0)?list.get(0):null;
	}
	public List<T> getList(D f) {
		return dao.selectByExample(f);
	}
	
	public Page<T> getpage(int pageCount,int pageNum,D f){
		int count = (int) dao.countByExample(f);
		Page<T> page = new Page<T>(pageCount,count,pageNum);
		String limit =page.getStart()+ "," +page.getPageCount();
		f.setLimit(limit);
		List<T> list = getList(f);
		page.setRows(list);
		return page;
	}
}
