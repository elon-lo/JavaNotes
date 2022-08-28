package com.yu.mybatis.test;

import com.yu.mybatis.mapper.SQLMapper;
import com.yu.mybatis.pojo.User;
import com.yu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author elonlo
 * @date 2022/8/21 11:53
 */
public class SQLMapperTest {

	@Test
	public void testGetUserByLike() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
		List<User> userList = mapper.getUserByLike("李");
		System.out.println(userList);
	}

	@Test
	public void testDeleteMore() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
		int i = mapper.deleteMore("3,4,5");
		System.out.println(i);
	}

	@Test
	public void testGetAllUser() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
		List<User> userList = mapper.getAllUser("t_user");
		System.out.println(userList);
	}

	@Test
	public void testInsertUser() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
		User user = new User(null, "aaa", "bbb", 10, 0, "aaa@163.com");
		mapper.insertUser(user);
		System.out.println(user);
	}
}
