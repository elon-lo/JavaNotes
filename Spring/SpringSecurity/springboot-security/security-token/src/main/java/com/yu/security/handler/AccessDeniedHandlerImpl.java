package com.yu.security.handler;

import com.alibaba.fastjson.JSON;
import com.yu.security.util.ResponseResult;
import com.yu.security.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权异常处理
 *
 * @author elonlo
 * @date 2023/7/9 21:38
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        // 定义异常返回信息
        ResponseResult<String> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "您的权限不足,请联系管理员", "");

        // 将异常信息转为json字符串
        String json = JSON.toJSONString(result);

        // 通过流写出
        WebUtils.renderString(response, json);
    }
}
