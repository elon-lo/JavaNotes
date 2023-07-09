package com.yu.security.handler;

import com.alibaba.fastjson.JSON;
import com.yu.security.util.ResponseResult;
import com.yu.security.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证异常处理
 *
 * @author elonlo
 * @date 2023/7/9 21:37
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        // 定义异常返回信息
        ResponseResult<String> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "用户认证失败", "");

        // 将异常信息转为json字符串
        String json = JSON.toJSONString(result);

        // 通过流写出
        WebUtils.renderString(response, json);
    }
}
