package com.yu.security.service;

import com.yu.security.domain.dto.LoginUserDTO;

import java.util.Map;

/**
 * 登录用户service接口
 *
 * @author elonlo
 * @date 2023/7/8 21:37
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param dto dto
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String, Object> login(LoginUserDTO dto);

    /**
     * 注销
     *
     * @return {@link String}
     */
    String logout();
}
