package com.yu.mybatis.mapper;

import com.yu.mybatis.domain.User;

import java.util.List;

/**
 * 用户mapper接口
 *
 * @author elonlo
 * @date 2022/7/16 18:59
 */
public interface UserMapper {


	/**
	 * 添加用户
	 *
	 * @return int  返回受影响的行数
	 */
	int insertUser();

	/**
	 * 更新用户
	 */
	void updateUser();

	/**
	 * 删除用户
	 */
	void deleteUser();

	/**
	 * 查询用户详情
	 *
	 * @return {@link User}
	 */
	User selectUserById();

	/**
	 * 查询用户列表
	 *
	 * @return {@link List}<{@link User}>
	 */
	List<User> selectList();
}
