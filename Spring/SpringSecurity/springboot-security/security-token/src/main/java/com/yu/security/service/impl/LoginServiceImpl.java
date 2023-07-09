package com.yu.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.yu.security.domain.dto.LoginUserDTO;
import com.yu.security.domain.entity.LoginUser;
import com.yu.security.domain.entity.User;
import com.yu.security.service.LoginService;
import com.yu.security.util.JwtUtils;
import com.yu.security.util.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 登录用户service接口实现
 *
 * @author elonlo
 * @date 2023/7/8 21:38
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    private final RedisUtils redisUtils;

    public LoginServiceImpl(AuthenticationManager authenticationManager, RedisUtils redisUtils) {
        this.authenticationManager = authenticationManager;
        this.redisUtils = redisUtils;
    }

    /**
     * 登录
     */
    @Override
    public Map<String, Object> login(LoginUserDTO dto) {
        // 1.用户名和密码构建成UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPasswd());

        // 2.使用AuthenticationManager的authentication方法进行用户认证,实际调用loadUserByUsername方法进行校验
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // 3.认证失败则给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误!");
        }

        // 4.获取认证后的用户信息,principal其实就是loadUserByUsername方法返回的信息
        LoginUser principal = (LoginUser) authenticate.getPrincipal();

        Long userId = Optional.ofNullable(principal).map(LoginUser::getUser).map(User::getId).orElse(null);

        // 5.认证通过则使用userId生成jwt保存到map中
        Map<String, Object> map = new HashMap<>(16);
        String token = JwtUtils.createJwtToken(String.valueOf(userId));
        map.put("token", token);

        // 6.把完整的用户信息存入redis
        redisUtils.set("login:" + userId, JSON.toJSONString(principal), 1, TimeUnit.HOURS);
        return map;
    }

    /**
     * 注销
     */
    @Override
    public String logout() {
        // 从SecurityContextHolder中获取authentication
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        // authentication获取用户信息
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();

        // 根据用户id删除redis数据
        redisUtils.delete("login:" + loginUser.getUser().getId());

        return "注销成功";
    }
}
