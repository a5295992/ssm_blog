package com.along.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.along.entity.Role;
import com.along.entity.RoleExample;

public class TestRoleMapper {
	private String path = "/mybatis-config-test.xml";
	private SqlSession session;
	private RoleMapper roleMapper;
	@Before
	public void setUp() throws Exception{
		InputStream inputStream = TestRoleMapper.class.getResourceAsStream(path);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		inputStream.close();
		session = sqlSessionFactory.openSession();
	}
	@After
	public void endSet() throws FileNotFoundException{
		session.close();
	}
	@Test
	public void testSelectByUserId(){
		roleMapper = session.getMapper(RoleMapper.class);
		String userId = "1";
		Role role = roleMapper.selectByUserId(userId);
		System.out.println(role);
	}
}
