package com.along.test.dao;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.along.common.DateUtil;
import com.along.dao.MenuMapper;
import com.along.entity.Menu;
import com.along.entity.MenuExample;

public class TestMenuDao {
	private String path = "/mybatis-config-test.xml";
	private SqlSession session;
	private MenuMapper menuMapper;

	@Before
	public void setUp() throws Exception {
		InputStream inputStream = TestMenuDao.class.getResourceAsStream(path);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		inputStream.close();
		session = sqlSessionFactory.openSession();
	}

	@After
	public void endSet() throws FileNotFoundException {
		session.close();
	}

	// @Test
	public void testGetByExample() {
		menuMapper = session.getMapper(MenuMapper.class);
		MenuExample example = new MenuExample();
		example.createCriteria().andPidEqualTo("0");
		List<Menu> menu = menuMapper.selectByExample(example);
		System.out.println(menu.get(0).getChildren());

	}

	@Test
	public void testAddMenu() {
		/*
		 * menuMapper = session.getMapper(MenuMapper.class); String name =
		 * "菜单管理"; String href = "a/a"; String pid = "0"; String createBy = "1";
		 * Date createDate = DateUtil.toUtilDate(); Date updateDate =
		 * DateUtil.toUtilDate();
		 * 
		 * String delFlag = "0"; String remarks = null; String updateBy = null;
		 * String id = UUID.randomUUID().toString().replace("-", ""); Menu
		 * record =new Menu(id, name, href, pid, createBy, createDate,
		 * updateDate, delFlag, remarks, updateBy);
		 * 
		 * menuMapper.insert(record );session.commit();
		 */

		Date updateDate = DateUtil.toUtilDate();
		System.out.println(updateDate);
	}
}
