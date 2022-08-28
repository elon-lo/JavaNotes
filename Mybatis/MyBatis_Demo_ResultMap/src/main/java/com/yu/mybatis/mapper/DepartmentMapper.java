package com.yu.mybatis.mapper;

import com.yu.mybatis.pojo.Department;
import org.apache.ibatis.annotations.Param;

/**
 * @author elonlo
 * @date 2022/8/21 20:03
 */
public interface DepartmentMapper {

	/**
	 * 分步查询
	 * 第二步:通过did查询员工所在的部门信息
	 *
	 * @param did 部门id
	 * @return {@link Department}
	 */
	Department getEmployeeAndDepartmentByStepTwo(@Param("did") Long did);

	/**
	 * 查询部门以及部门下面的所有员工信息
	 *
	 * @param did 部门id
	 * @return {@link Department}
	 */
	Department getDepartmentAndEmployeeByDid(@Param("did") Long did);

	/**
	 * 分步查询
	 * 第一步:通过did查询部门信息
	 *
	 * @param did 部门id
	 * @return {@link Department}
	 */
	Department getDepartmentAndEmployeeByStepOne(@Param("did") Long did);
}
