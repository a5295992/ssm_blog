package com.along.dao;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.along.entity.Menu;
import com.along.entity.MenuExample;

public class TestMenuDao {
	private String path = "/mybatis-config-test.xml";
	private SqlSession session;
	private MenuMapper menuMapper;
	@Before
	public void setUp() throws Exception{
		InputStream inputStream = TestMenuDao.class.getResourceAsStream(path);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		inputStream.close();
		session = sqlSessionFactory.openSession();
	}
	@After
	public void endSet() throws FileNotFoundException{
		session.close();
	}
	
	
//	@Test                                                                                
	public void testGetByExample(){
		menuMapper = session.getMapper(MenuMapper.class);
		MenuExample example = new MenuExample();
		example.createCriteria().andPidEqualTo("0");
		List<Menu> menu = menuMapper.selectByExample(example );
		System.out.println(menu.get(0).getChildren());
		
		
	}
}
