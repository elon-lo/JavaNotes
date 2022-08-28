package com.yu.mybatis.test;

import com.yu.mybatis.mapper.CacheMapper;
import com.yu.mybatis.mapper.DynamicSQLMapper;
import com.yu.mybatis.mapper.EmployeeMapper;
import com.yu.mybatis.pojo.Employee;
import com.yu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author elonlo
 * @date 2022/8/25 16:30
 */
public class CacheMapperTest {

	@Test
	public void testGetEmployeeByFirstLevelCache() throws Exception {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		CacheMapper cacheMapper1 = sqlSession.getMapper(CacheMapper.class);
		System.out.println(cacheMapper1.getEmployeeByFirstLevelCache(1L));

		CacheMapper cacheMapper2 = sqlSession.getMapper(CacheMapper.class);
		System.out.println(cacheMapper2.getEmployeeByFirstLevelCache(1L));
	}

	@Test
	public void testGetEmployeeBySecondLevelCache() throws Exception {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

		SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
		CacheMapper cacheMapper1 = sqlSession1.getMapper(CacheMapper.class);
		System.out.println(cacheMapper1.getEmployeeByFirstLevelCache(1L));
		sqlSession1.commit();


		SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
		CacheMapper cacheMapper2 = sqlSession2.getMapper(CacheMapper.class);
		System.out.println(cacheMapper2.getEmployeeByFirstLevelCache(1L));
//		sqlSession2.commit();
	}
}
