package com.yu.mybatis.test;

import com.yu.mybatis.mapper.EmployeeMapper;
import com.yu.mybatis.pojo.Employee;
import com.yu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author elonlo
 * @date 2022/8/21 20:11
 */
public class EmployeeMapperTest {

	@Test
	public void testGetAllEmployee() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		List<Employee> employeeList = mapper.getAllEmployee();
		employeeList.forEach(System.out::println);
	}

	@Test
	public void testGetEmployeeAndDepartmentByEid() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee employee = mapper.getEmployeeAndDepartmentByEid(3L);
		System.out.println(employee);
	}

	@Test
	public void testGetEmployeeAndDepartmentByStep() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee employee = mapper.getEmployeeAndDepartmentByStepOne(4L);
		System.out.println(employee.getEmpName());
		System.out.println("=======================================");
		System.out.println(employee.getDepartment());
	}
}
