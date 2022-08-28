package com.yu.mybatis.mapper;

import com.yu.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author elonlo
 * @date 2022/8/16 23:23
 */
public interface SelectMapper {

	/**
	 * 根据id查询用户详情
	 * 返回值可以用实体类对象或者集合类型接收
	 *
	 * @param id id
	 * @return {@link User}
	 */
	User getUserById(@Param("id") Integer id);
//	List<User> getUserById(@Param("id") Integer id);

	/**
	 * 查询所有用户
	 * 返回值只能使用集合类型接收
	 *
	 * @return {@link List}<{@link User}>
	 */
	Set<User> getAllUser();

	Integer getCount();

	/**
	 * 根据id查询用户详情(Map类型)
	 *
	 * @param id id
	 * @return {@link Map}<{@link String}, {@link Object}>
	 */
	Map<String,Object> getUserByIdToMap(@Param("id") Integer id);

	/**
	 * 查询所有用户
	 * 1、可以使用map类型的集合接收
	 * 2、可以使用@MapKey注解,此时可以将每条数据转换为map集合的值,以某个字段为键,放在同一个map中
	 *
	 * @return {@link Map}<{@link String}, {@link Object}>
	 */
//	List<Map<String,Object>> getAllUserToMap();

	@MapKey("username")
	Map<String,Object> getAllUserToMap();
}
