package com.yu.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.shiro.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author elonlo
 * @since 2023-06-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询角色列表
     *
     * @param username 用户名
     * @return {@link List}<{@link String}>
     */
    List<String> selectRolesByUsername(@Param("username") String username);

    /**
     * 根据角色名称数组查询权限列表
     *
     * @param roleNames 角色名称数组
     * @return {@link List}<{@link String}>
     */
    List<String> selectPermissionsByRoleNames(@Param("roleNames") String[] roleNames);
}
