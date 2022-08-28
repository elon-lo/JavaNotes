package com.yu.mybatis.test;

import com.yu.mybatis.mapper.ParameterMapper;
import com.yu.mybatis.pojo.User;
import com.yu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author elonlo
 * @date 2022/8/14 22:43
 */
public class TestSqlSession {

	/**
	 * ${}本质是字符串拼接
	 * #{}本质是占位符赋值
	 * MyBatis获取参数值的各种情况
	 * 1、Mapper接口方法的参数为单个的字面量类型
	 * 可以通过${}和#{}以任意的名称获取参数值
	 *
	 **/

	@Test
	public void test() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
		List<User> userList = mapper.getAllUser();
		userList.forEach(System.out::println);
	}

	@Test
	public void testCheckLoginByParam() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
		User user = mapper.checkLoginByParam("aaa","bbb");
		System.out.println(user);
	}

	@Test
	public void testInsertParamUser() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
		int i = mapper.insertParamUser(new User(null,"bbb","234",20,0,"234@qq.com"));
		System.out.println(i);
	}

	@Test
	public void testCheckLoginByMap() {
		Map<String,Object> map = new LinkedHashMap<>(16);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
		map.put("username","zs");
		map.put("password","123456");
		User user = mapper.checkLoginByMap(map);
		System.out.println(user);
	}

	@Test
	public void testCheckLogin() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
		User user = mapper.checkLogin("zs","123456");
		System.out.println(user);
	}

	@Test
	public void testUsername() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
		User user = mapper.getUserByUsername("李四");
		System.out.println(user);
	}

	@Test
	public void testJDBC() throws Exception {
		String username = "zs";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://49.234.25.81:3307/mybatis?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai", "root", "123456");
		PreparedStatement ps = connection.prepareStatement("select * from t_user where username = ?");
		ps.setString(1,username);
		ResultSet resultSet = ps.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "," + resultSet.getString(2)+ "," + resultSet.getString(3)+ "," + resultSet.getString(4)+ "," + resultSet.getString(5)+ "," + resultSet.getString(6));
		}
	}
}
