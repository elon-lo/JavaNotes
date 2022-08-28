package com.yu.mybatis.mapper;

import com.yu.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author elonlo
 * @date 2022/8/21 20:03
 */
public interface EmployeeMapper {

	/**
	 * 查询所有员工
	 *
	 * @return {@link List}<{@link Employee}>
	 */
	List<Employee> getAllEmployee();

	/**
	 * 根据员工id查询员工信息及员工所在的部门信息
	 *
	 * @param eid 员工id
	 * @return {@link Employee}
	 */
	Employee getEmployeeAndDepartmentByEid(@Param("eid") Long eid);

	/**
	 * 分步查询
	 * 通过eid查询员工的信息
	 *
	 * @param eid 员工id
	 * @return {@link Employee}
	 */
	Employee getEmployeeAndDepartmentByStepOne(@Param("eid") Long eid);

	/**
	 * 分步查询
	 * 第二步:通过did查询部门下的所有员工信息
	 *
	 * @param did 部门id
	 * @return {@link List}<{@link Employee}>
	 */
	List<Employee> getDepartmentAndEmployeeByStepTwo(@Param("did") Long did);
}
