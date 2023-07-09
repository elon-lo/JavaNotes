package com.yu.security.expression;

import com.yu.security.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 自定义权限校验方法
 *
 * @author elonlo
 * @date 2023/7/9 23:05
 */
@Component("ex")
public class SecurityExpression {

    public boolean hasAuthority(String authString) {
        // 获取权限集合
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Set<String> permissions = loginUser.getPermissions();

        // 判断字符串是否包含在权限集合中
        return permissions.contains(authString);
    }
}
