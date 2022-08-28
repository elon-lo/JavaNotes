package com.yu.mybatis.test;

import com.yu.mybatis.mapper.EmpMapper;
import com.yu.mybatis.pojo.Emp;
import com.yu.mybatis.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author elonlo
 * @date 2022/8/27 17:53
 */
public class MBGTest {

	@Test
	public void testMBG() {
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

			// 查询所有员工
			/*List<Emp> empList = mapper.selectByExample(null);
			empList.forEach(System.out::println);*/

			// 根据条件查询
			/*EmpExample example = new EmpExample();
			example.createCriteria().andEmpNameEqualTo("zs").andAgeGreaterThan(14);
			example.or().andDidEqualTo(1L);
			List<Emp> empList = mapper.selectByExample(example);
			empList.forEach(System.out::println);*/

//			mapper.updateByPrimaryKey(new Emp(1L, "admin", null, 1, "admin@163.com", 5L));
			mapper.updateByPrimaryKeySelective(new Emp(1L, "admin", null, 1, "admin@163.com", 5L));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
