package com.yu.mybatis.test;

import com.yu.mybatis.mapper.DepartmentMapper;
import com.yu.mybatis.pojo.Department;
import com.yu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author elonlo
 * @date 2022/8/22 19:27
 */
public class DepartmentMapperTest {

	@Test
	public void testDetDepartmentAndEmployeeByDid() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
		Department department = mapper.getDepartmentAndEmployeeByDid(3L);
		System.out.println(department);

	}

	@Test
	public void testGetDepartmentAndEmployeeByStep() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
		Department department = mapper.getDepartmentAndEmployeeByStepOne(3L);
		System.out.println(department.getDeptName());
	}
}
