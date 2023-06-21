package com.yu.shiro.factory;

import java.util.LinkedHashMap;

/**
 * 自定义过滤器链
 *
 * @author elonlo
 * @date 2023/6/20 6:35
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        // 这里的过滤器信息一般是从数据库中查询出来的
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("/login.jsp", "anon");
        hashMap.put("/shiro/login", "anon");
        hashMap.put("/shiro/logout", "logout");
        hashMap.put("/user.jsp", "authc,roles[user]");
        hashMap.put("/admin.jsp", "authc,roles[admin]");
        hashMap.put("/list.jsp", "user");
        hashMap.put("/**", "authc");

        return hashMap;
    }
}
