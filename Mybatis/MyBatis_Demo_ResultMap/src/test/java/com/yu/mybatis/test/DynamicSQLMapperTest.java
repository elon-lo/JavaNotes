package com.yu.mybatis.test;

import com.yu.mybatis.mapper.DynamicSQLMapper;
import com.yu.mybatis.pojo.Employee;
import com.yu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author elonlo
 * @date 2022/8/23 14:51
 */
public class DynamicSQLMapperTest {

	@Test
	public void testGetEmployeeByCondition() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
		List<Employee> employeeList = mapper.getEmployeeByCondition(new Employee(null, null, null, null, null, null,null));
		System.out.println(employeeList);
	}

	@Test
	public void testGetEmployeeByChoose() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
		List<Employee> employeeList = mapper.getEmployeeByChoose(new Employee(null, null, null, null, "", null,null));
		System.out.println(employeeList);
	}

	@Test
	public void testDeleteMoreEmployeeByArray() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
		int i = mapper.deleteMoreEmployeeByArray(new Long[]{5L, 6L});
		System.out.println(i);
	}

	@Test
	public void testInsertMoreEmployeeByCollection() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
		Employee emp1 = new Employee(15L, "zs1", 12, 1, "123@qq.com", 1L, null);
		Employee emp2 = new Employee(16L, "zs2", 15, 1, "123@qq.com", 1L, null);
		Employee emp3 = new Employee(17L, "zs3", 18, 1, "123@qq.com", 1L, null);

		List<Employee> employeeList = Arrays.asList(emp1, emp2, emp3);
		int i = mapper.insertMoreEmployeeByCollection(employeeList);
		System.out.println(i);
	}
}
