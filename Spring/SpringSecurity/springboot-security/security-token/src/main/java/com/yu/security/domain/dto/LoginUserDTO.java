package com.yu.security.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户dto
 *
 * @author elonlo
 * @date 2023/7/8 21:33
 */
@Data
public class LoginUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passwd;
}
