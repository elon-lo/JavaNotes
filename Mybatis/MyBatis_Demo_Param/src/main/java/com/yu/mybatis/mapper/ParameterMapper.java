package com.yu.mybatis.mapper;

import com.yu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户mapper接口
 *
 * @author elonlo
 * @date 2022/7/16 18:59
 */
public interface ParameterMapper {

	User getUserByUsername(String username);

	List<User> getAllUser();

	User checkLogin(String username, String password);

	User checkLoginByMap(Map<String, Object> map);

	int insertParamUser(User user);

	User checkLoginByParam(@Param("name") String username, @Param("passwd") String password);
}
