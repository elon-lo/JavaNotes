package com.yu.mybatis.test;

import com.yu.mybatis.mapper.SelectMapper;
import com.yu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author elonlo
 * @date 2022/8/16 23:27
 */
public class SelectMapperTest {

	@Test
	public void testGetUserById() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
		System.out.println(mapper.getUserById(4));
	}

	@Test
	public void testGetAllUser() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
		System.out.println(mapper.getAllUser());
	}

	@Test
	public void testGetCount() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
		System.out.println(mapper.getCount());
	}

	@Test
	public void testGetUserByIdToMap() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
		System.out.println(mapper.getUserByIdToMap(4));
	}

	@Test
	public void testGetAllUserToMap() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
		System.out.println(mapper.getAllUserToMap());
	}

}
