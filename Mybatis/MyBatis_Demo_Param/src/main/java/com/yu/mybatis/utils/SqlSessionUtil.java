package com.yu.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * sqlsession工具类
 *
 * @author elonlo
 * @date 2022/8/14 22:38
 */
public class SqlSessionUtil {

	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null;

		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			sqlSession = factory.openSession(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlSession;
	}
}
