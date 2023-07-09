package com.yu.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * jwt工具类
 *
 * @author elonlo
 * @date 2023/7/8 14:39
 */
public class JwtUtils {

    /**
     * token过期时间,一个小时
     */
    public static final Long EXPIRE = 1000 * 60 * 60L;

    /**
     * token秘钥
     */
    public static final String JWT_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /**
     * 传入参数，获取jwt字符串
     *
     * @param id 参数之id
     * @return
     */
    public static String createJwtToken(String id) {

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .setId(id)

                /*
                 * 在这里可以一直添加参数
                 */
//                .claim("id", id)
//                .claim("name", "zs")

                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    /**
     * 判断token是否存在与有效，无效会抛异常
     */
    public static boolean checkToken(String jwtToken) {
        if (!StringUtils.hasText(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效，无效会抛异常
     *
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if (!StringUtils.hasText(jwtToken)) {
                return false;
            }
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 解析token
     *
     * @param request 请求头信息
     * @return {@link Claims}
     */
    public static Claims parseJwtToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            return null;
        }

        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
