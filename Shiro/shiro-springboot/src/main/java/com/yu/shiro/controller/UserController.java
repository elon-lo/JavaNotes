package com.yu.shiro.controller;


import com.yu.shiro.domain.dto.LoginUserDTO;
import com.yu.shiro.service.IUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author elonlo
 * @since 2023-06-22
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录
     */
    @PostMapping("/userLogin")
    public String login(@ModelAttribute LoginUserDTO dto, HttpSession session) {
        boolean flag = userService.login(dto, session);
        if (flag) {
            return "main";
        }
        return "error";
    }

    /**
     * 用于测试rememberMe功能是否生效
     */
    @GetMapping("/userLoginRememberMe")
    public String userLoginRememberMe(HttpSession session) {
        session.setAttribute("user", "rememberMe");
        return "userLoginRememberMe";
    }

    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/userLoginRoles")
    public String userLoginRoles() {
        System.out.println("验证用户角色信息");
        return "该用户有admin角色";
    }

    @RequiresPermissions("user:add")
    @ResponseBody
    @GetMapping("/userLoginPermissions")
    public String userLoginPermissions() {
        System.out.println("验证用户权限信息");
        return "该用户有user:add权限";
    }
}
