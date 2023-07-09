package com.yu.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.yu.security.domain.entity.LoginUser;
import com.yu.security.util.JwtUtils;
import com.yu.security.util.RedisUtils;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * JWT过滤器
 *
 * @author elonlo
 * @date 2023/7/9 11:36
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final RedisUtils redisUtils;

    public JwtFilter(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String userId;

        try {
            // 从请求头获取token并解析
            Claims claims = JwtUtils.parseJwtToken(request);

            // token不存在则直接放行,否则继续执行
            if (Objects.isNull(claims)) {
                filterChain.doFilter(request, response);
                return;
            }
            userId = claims.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取token非法");
        }

        // 从redis中获取用户信息并将json字符串转为对象
        Object o = redisUtils.get("login:" + userId);
        if (Objects.isNull(o)) {
            throw new RuntimeException("用户未登录");
        }
        LoginUser loginUser = JSONObject.parseObject(o.toString(), LoginUser.class);

        // 将对象信息构造到UsernamePasswordAuthenticationToken,这里注意必须使用三个参数的构造方法,用于设置authenticated为true
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());

        // 将authenticationToken存入SecurityContextHolder
        // TODO 获取权限封装到SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
