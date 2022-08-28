package com.yu.mybatis;

import com.yu.mybatis.mapper.UserMapper;
import com.yu.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis测试类
 *
 * @author elonlo
 * @date 2022/7/16 21:47
 */
public class MyBatisTest {

	/**
	 * sqlSession默认不提交事务,可通过sqlSessionFactory.openSession(true)设置自动提交事务
	 *
	 * 测试添加用户
	 */
	@Test
	public void testAdd() throws IOException {
		// 加载核心配置文件
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		// 获取SqlSessionFactoryBuilder
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
		// 获取sqlSessionFactory
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
		// 获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		// 获取mapper接口实例
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 添加用户
		int result = userMapper.insertUser();
		// 手动提交事务
//		sqlSession.commit();
		System.out.println("result: " + result);
	}

	@Test
	public void testCRUD() throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 更新用户
//		userMapper.updateUser();
		// 删除用户
//		userMapper.deleteUser();
		// 查询用户详情
//		User user = userMapper.selectUserById();
		// 查询所有用户
		List<User> userList = userMapper.selectList();
		userList.forEach(System.out::println);
	}
}
