package com.yu.shiro.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

import java.util.Date;

/**
 * Shiro服务层
 *
 * @author elonlo
 * @date 2023/6/19 23:44
 */
public class ShiroService {

    @RequiresRoles({"admin"})
    public void ShiroAnnotationTest() {
        System.out.println("当前时间: " + new Date());

        Session session = SecurityUtils.getSubject().getSession();
        Object value = session.getAttribute("name");
        System.out.println("service session: " + value);
    }
}
