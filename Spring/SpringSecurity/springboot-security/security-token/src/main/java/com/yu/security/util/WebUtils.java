package com.yu.security.util;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * web工具类
 *
 * @author elonlo
 * @date 2023/7/8 15:08
 */
public class WebUtils {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param str      待渲染的字符串
     * @return {@link String}
     */
    public static String renderString(HttpServletResponse response, String str) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json");
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
