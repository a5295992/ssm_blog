package com.along.serviceImpl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.along.common.JSONutil;
import com.along.common.Page;
import com.along.dao.CrudDao;
import com.along.entity.DataEntity;
import com.along.entity.Example;
import com.along.service.BaseService;

public abstract class CrudService<G extends CrudDao<T, D>, T extends DataEntity<T>, D extends Example<D>>
		extends BaseService<T> {

	@Autowired
	protected G dao;

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

	public int delete(String id) {
		return dao.deleteByPrimaryKey(id);
	}
	
	/**
	 * 改用逻辑删除 此方法 供 清理调用
	 * 
	 * @param example
	 * @return
	 */
	public int deletes(D example) {
		return dao.deleteByExample(example);
	}

	/**
	 * 描述 :更新 单个的数据
	 * 
	 * @param t
	 *            更改后的对象
	 * @return 返回操作结果集的数量
	 */
	public int update(T t) {
		if (t.getId() == null) {
			return add(t);
		} else {
			setUpdateValue(t);
			return dao.updateByPrimaryKeySelective(t);
		}
	}

	/**
	 * 描述:根据 传入的条件 批量更新数据库的数据
	 * 
	 * @param t
	 *            携带更改信息
	 * @param f
	 *            携带批量的操作对象信息
	 * @return 返回 操作操作成功的结果集数据统计
	 */
	public int updates(T t, D f) {
		setUpdateValue(t);
		return dao.updateByExampleSelective(t, f);
	}

	/**
	 * 批量更新数据库表数据
	 * 
	 * @param list
	 *            验证过的数据集合 将数据验证步骤交给BaseController
	 * @return 返回结果集的 个数统计
	 */
	public int update(List<T> list) {
		int result = 0;
		for (T t : list) {
			result = result + update(t);
		}
		return result;
	}

	/**
	 * 查找单个的对象
	 * 
	 * @param f
	 *            自定义查询条件
	 * @return 单个对象
	 */

	public T getSinge(D f) {
		List<T> list = getList(f);
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}

	/**
	 * 描述:查找 数据集合的常用方法
	 * 
	 * @param f
	 *            查询条件对象
	 * @return List数据集合
	 */

	public List<T> getList(D f) {
		return dao.selectByExample(f);
	}

	/**
	 * 描述:获取自定义分页对象的常用方法
	 * 
	 * @param pageCount
	 *            分页大小
	 * @param pageNum
	 *            当前页码
	 * @param f
	 *            查询参数对象
	 * @return 自定义分页对象
	 */
	public Page<T> getpage(int pageCount, int pageNum, D f) {
		int count = (int) dao.countByExample(f);
		Page<T> page = new Page<T>(pageCount, count, pageNum);
		String limit = page.getStart() + "," + page.getPageCount();
		f.setLimit(limit);
		List<T> list = getList(f);
		page.setRows(list);
		return page;
	}

	/**
	 * 描述:获取自定义分页对象的常用方法
	 * 
	 * @param pageCount
	 *            分页大小
	 * @param pageNum
	 *            当前页码
	 * @param f
	 *            查询参数对象
	 * @return 自定义分页对象的Json对象形式
	 */
	public JSONObject getPageToJSONFormatSqlDate(int pageCount, int pageNum, D f) {
		Page<T> t = getpage(pageCount, pageNum, f);
		return JSONutil.formatJsonBeanDate(t);
	}
}
