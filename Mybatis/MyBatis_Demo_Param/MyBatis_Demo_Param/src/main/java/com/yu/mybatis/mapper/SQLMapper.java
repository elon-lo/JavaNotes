package com.yu.mybatis.mapper;

import com.yu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author elonlo
 * @date 2022/8/21 11:00
 */
public interface SQLMapper {

	/**
	 * 根据用户名模糊查询用户
	 *
	 * @param username 用户名
	 * @return {@link List}<{@link User}>
	 */
	List<User> getUserByLike(@Param("username") String username);

	/**
	 * 批量删除
	 *
	 * @param ids id
	 * @return int
	 */
	int deleteMore(@Param("ids") String ids);

	/**
	 * 动态设置表名
	 *
	 * @param tableName 表名
	 * @return {@link List}<{@link User}>
	 */
	List<User> getAllUser(@Param("tableName") String tableName);

	/**
	 * 添加用户
	 *
	 * @param user 用户
	 * @return int
	 */
	int insertUser(User user);
}
