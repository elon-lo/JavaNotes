package com.yu.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.shiro.domain.dto.LoginUserDTO;
import com.yu.shiro.domain.entity.User;
import com.yu.shiro.mapper.UserMapper;
import com.yu.shiro.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author elonlo
 * @since 2023-06-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户名查询用户
     */
    @Override
    public User getUserInfoByName(String name) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, name));
    }

    /**
     * 根据用户名查询角色列表
     */
    @Override
    public List<String> findRolesByUsername(String username) {
        return userMapper.selectRolesByUsername(username);
    }

    /**
     * 根据角色名称数组查询权限列表
     */
    @Override
    public List<String> findPermissionsByRoleNames(String[] roleNames) {
        return userMapper.selectPermissionsByRoleNames(roleNames);
    }

    /**
     * 登录
     */
    @Override
    public boolean login(LoginUserDTO dto, HttpSession session) {
        // 1.获取subject对象
        Subject subject = SecurityUtils.getSubject();

        // 2.使用用户名和密码构建UsernamePasswordToken对象
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPasswd(), dto.getRememberMe());

        // 3.调用登录方法
        try {
            subject.login(token);
            session.setAttribute("user", token.getPrincipal());
            return true;
        } catch (AuthenticationException e) {
            LOGGER.error("登录失败: {}", e);
            e.printStackTrace();
        }
        return false;
    }
}
