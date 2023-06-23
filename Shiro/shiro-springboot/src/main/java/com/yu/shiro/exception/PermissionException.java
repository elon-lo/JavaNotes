package com.yu.shiro.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 认证授权异常类
 *
 * @author elonlo
 * @date 2023/6/23 20:06
 */
@RestControllerAdvice
public class PermissionException {

    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedException(Exception e) {
        return "无权限!";
    }

    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException(Exception e) {
        return "权限验证失败!";
    }
}
