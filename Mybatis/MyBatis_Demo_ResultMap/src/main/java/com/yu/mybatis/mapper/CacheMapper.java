package com.yu.mybatis.mapper;

import com.yu.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @author elonlo
 * @date 2022/8/25 16:26
 */
public interface CacheMapper {

	/**
	 * 通过一级缓存查询员工信息
	 *
	 * @param eid 员工id
	 * @return {@link Employee}
	 */
	Employee getEmployeeByFirstLevelCache(@Param("eid") Long eid);

	/**
	 * 添加员工
	 *
	 * @param employee 员工
	 * @return int
	 */
	int insertEmployee(Employee employee);
}
