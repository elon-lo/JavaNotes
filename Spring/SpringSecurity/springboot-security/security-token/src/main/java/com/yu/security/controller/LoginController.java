package com.yu.security.controller;

import com.yu.security.domain.dto.LoginUserDTO;
import com.yu.security.service.LoginService;
import com.yu.security.util.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 登录控制器
 *
 * @author elonlo
 * @date 2023/7/8 21:28
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginUserDTO dto) {
        Map<String, Object> map = loginService.login(dto);
        return new ResponseResult<>(200, "登录成功", map);
    }

    /**
     * 注销
     *
     * @return {@link ResponseResult}<{@link String}>
     */
    @GetMapping("/logout")
    public ResponseResult<String> logout() {
        String msg = loginService.logout();
        return new ResponseResult<>(200, msg);
    }
}
