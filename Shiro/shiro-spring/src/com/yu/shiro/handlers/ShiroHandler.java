package com.yu.shiro.handlers;

import com.yu.shiro.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Shiro处理器
 *
 * @author elonlo
 * @date 2023/6/18 20:49
 */
@Controller
@RequestMapping("/shiro")
public class ShiroHandler {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/ShiroAnnotationTest")
    public String ShiroAnnotationTest(HttpSession session) {
        session.setAttribute("name", session);
        shiroService.ShiroAnnotationTest();
        return "redirect:/index.jsp";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {

        // 获取subject
        Subject subject = SecurityUtils.getSubject();

        // 判断当前用户是否已经被认证
        if (!subject.isAuthenticated()) {
            // 将用户名和密码封装为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            // 记住我
            token.setRememberMe(true);

            try {
                // 登录
                subject.login(token);
            } catch (AuthenticationException e) {
                System.out.println("登录失败: " + e.getMessage());
            }
        }
        return "redirect:/index.jsp";
    }
}
