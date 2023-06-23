package com.yu.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.shiro.domain.dto.LoginUserDTO;
import com.yu.shiro.domain.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author elonlo
 * @since 2023-06-22
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     *
     * @param name 名字
     * @return {@link User}
     */
    User getUserInfoByName(String name);


    /**
     * 登录
     *
     * @param dto     用户登录DTO
     * @param session 会话
     * @return boolean
     */
    boolean login(LoginUserDTO dto, HttpSession session);

    /**
     * 根据用户名查询角色列表
     *
     * @param username 用户名
     * @return {@link List}<{@link String}>
     */
    List<String> findRolesByUsername(String username);


    /**
     * 根据角色名称数组查询权限列表
     *
     * @param roleNames 角色名称数组
     * @return {@link List}<{@link String}>
     */
    List<String> findPermissionsByRoleNames(String[] roleNames);
}
