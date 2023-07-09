package com.yu.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yu.security.domain.entity.LoginUser;
import com.yu.security.domain.entity.User;
import com.yu.security.mapper.MenuMapper;
import com.yu.security.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户详情服务实现类(自定义认证)
 *
 * @author elonlo
 * @date 2023/7/8 16:28
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UserMapper userMapper;

    private final MenuMapper menuMapper;

    public UserDetailsServiceImpl(UserMapper userMapper, MenuMapper menuMapper) {
        this.userMapper = userMapper;
        this.menuMapper = menuMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 根据用户名查询用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException("该用户不存在!");
//        }
        // TODO: 2023/7/8  校验权限
//        List<String> permissions = Arrays.asList("test", "admin");
        List<String> permissions = menuMapper.selectPermsByUserId(user.getId());
        Set<String> permissionList = new HashSet<>(permissions);

        return new LoginUser(user, permissionList);
    }
}
