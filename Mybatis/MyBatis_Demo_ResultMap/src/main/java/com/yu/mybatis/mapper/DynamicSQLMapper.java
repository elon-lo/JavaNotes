package com.yu.mybatis.mapper;

import com.yu.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author elonlo
 * @date 2022/8/23 14:45
 */
public interface DynamicSQLMapper {

	/**
	 * 动态SQL:测试choose
	 *
	 * @param employee 查询条件
	 * @return {@link List}<{@link Employee}>
	 */
	List<Employee> getEmployeeByChoose(Employee employee);

	/**
	 * 动态SQL:多条件查询
	 *
	 * @param employee 查询条件
	 * @return {@link List}<{@link Employee}>
	 */
	List<Employee> getEmployeeByCondition(Employee employee);

	/**
	 * 通过数组方式批量删除员工
	 *
	 * @param eIds 员工id数组
	 * @return int
	 */
	int deleteMoreEmployeeByArray(@Param("eIds") Long[] eIds);

	/**
	 * 通过集合方式批量添加员工信息
	 *
	 * @param empList 员工信息集合
	 * @return int
	 */
	int insertMoreEmployeeByCollection(@Param("empList") List<Employee> empList);
}
